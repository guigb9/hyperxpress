package com.bandtec.hyperxpress.hyperxpressproject.view;

import com.mercadopago.resources.Preference;

public class RetornoMpDTO {
    private String urlMP;
    private String preferenceId;

    public RetornoMpDTO(Preference preference) {
        this.preferenceId = preference.getId();
        this.urlMP = preference.getInitPoint();
    }

    public String getUrlMP() {
        return urlMP;
    }

    public String getPreferenceId() {
        return preferenceId;
    }
}
