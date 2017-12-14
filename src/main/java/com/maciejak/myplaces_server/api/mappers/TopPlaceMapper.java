package com.maciejak.myplaces_server.api.mappers;

import com.maciejak.myplaces_server.api.dto.response.TopPlaceResponse;
import com.maciejak.myplaces_server.api.dto.response.TopPlaceResponseList;
import com.maciejak.myplaces_server.entity.TopPlace;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TopPlaceMapper {

    TopPlaceMapper INSTANCE = Mappers.getMapper(TopPlaceMapper.class);

    TopPlaceResponse topPlaceToTopPlaceResponse(TopPlace topPlace);

    TopPlaceResponseList topPlaceToTopPlaceResponseList(TopPlace topPlace);
}
