package com.soft.credit911.network;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "Unavailable Internet";
    }

}
