package com.maciejak.myplaces_server.api.dto.request;

import java.util.List;

public class IdsRequest {

    private List<Long> ids;

    public IdsRequest() {
    }

    public IdsRequest(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
