package com.maciejak.myplaces_server.utils;

import com.maciejak.myplaces_server.entity.TopPlace;
import com.maciejak.myplaces_server.entity.TopPlacePhoto;
import com.maciejak.myplaces_server.repositories.TopPlaceRepository;
import com.maciejak.myplaces_server.services.TopPlacesService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TopPlacesParser {

    public static final String TOP_PLACES_BASE_URL = "https://travel.usnews.com";
    public static final String TOP_PLACES_URL = TOP_PLACES_BASE_URL + "/rankings/best-europe-vacations/";

    private TopPlacesService topPlacesService;

    public TopPlacesParser(TopPlacesService topPlacesService) {
        this.topPlacesService = topPlacesService;
    }

    public void fillTopPlaces(){
        removeOldRanking();
        parseHtml();
    }

    private void removeOldRanking(){
        topPlacesService.clear();
    }

    private void parseHtml(){
        List<TopPlace> topPlaces = new ArrayList<>();

        Element body = createMainElement(TOP_PLACES_URL);
        if (body != null){
            List<Element> elementList = body.getElementsByClass("ranking-element row collapse block-looser rank-voting-item");

            for (Element element : elementList){
                TopPlace topPlace = new TopPlace();
                topPlace.setPhotos(parseTopPlacePhotos(element, topPlace));
                topPlace.setId(parsePlaceId(element));
                topPlace.setName(parsePlaceName(element));
                topPlace.setDescription(parseDescription(element));
                topPlace.setRank(parseDataRank(element));
                topPlace.setMainPhoto(parseMainPhoto(element));
                topPlaces.add(topPlace);
            }
        }

        topPlacesService.save(topPlaces);
    }

    private List<TopPlacePhoto> parseTopPlacePhotos(Element element, TopPlace topPlace) {
        List<TopPlacePhoto> topPlacePhotos = new ArrayList<>();
        Element linkToPhotosElement = element.getElementsByAttributeValue("data-analytics-link-type", "photos").first();
        String link = linkToPhotosElement.attr("href");
        String fullLink = TOP_PLACES_BASE_URL + link;

        Element body = createMainElement(fullLink);
        Element photosContent = body.getElementById("destination-photos-content");
        List<Element> photosContentElements = photosContent.getElementsByClass("slideshow-target js-slideshow-target border slideshow-image-box block-tight flex-row flex-ungrid small-center small-middle");
        for (Element photoElement : photosContentElements){
            TopPlacePhoto topPlacePhoto = new TopPlacePhoto();
            topPlacePhoto.setPhoto(parseTopPlacePhoto(photoElement));
            topPlacePhoto.setTopPlace(topPlace);
            topPlacePhotos.add(topPlacePhoto);
        }
        return topPlacePhotos;
    }

    private String parseTopPlacePhoto(Element photoElement) {
        Element imgElement = photoElement.getElementsByClass("slideshow-image full-width block-tight").first();
        String url = imgElement.attr("src");
        if (url.equals(""))
            url = imgElement.attr("data-lazy");
        String fullUrl = TOP_PLACES_BASE_URL + url;
        return fullUrl;
    }

    private Element createMainElement(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.body();
    }

    private String parsePlaceName(Element element){
        return element.attr("data-destination-name");
    }

    private Integer parseDataRank(Element element){
        return Integer.parseInt(element.attr("data-rank"));
    }

    private Long parsePlaceId(Element element){
        return Long.valueOf(element.attr("data-destination-id"));
    }

    private String parseDescription(Element element){
        Element descrBlock = element.getElementsByClass("show-for-medium-up text-small block-tight").first();
        descrBlock.selectFirst("a").remove();
        descrBlock.selectFirst("strong").remove();
        return descrBlock.text();
    }

    private String parseMainPhoto(Element element){
        Element picture = element.selectFirst("picture");
        Element source = picture.selectFirst("source");
        return TOP_PLACES_BASE_URL + source.attr("srcset");
    }

}
