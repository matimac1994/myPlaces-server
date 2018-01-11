package com.maciejak.myplaces_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    @Value("${my_places.files.directory}")
    private String rootFilesDirectory;

    @Value("${my_places.files.image.format}")
    private String imagesFormat;

    @Value("${my_places.server.host}")
    private String serverHost;

    @Value("${my_places.files.image.endpoint}")
    private String endpointImage;

    public String getRootFilesDirectory() {
        return rootFilesDirectory;
    }

    public void setRootFilesDirectory(String rootFilesDirectory) {
        this.rootFilesDirectory = rootFilesDirectory;
    }

    public String getImagesFormat() {
        return imagesFormat;
    }

    public void setImagesFormat(String imagesFormat) {
        this.imagesFormat = imagesFormat;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getEndpointImage() {
        return endpointImage;
    }

    public void setEndpointImage(String endpointImage) {
        this.endpointImage = endpointImage;
    }
}
