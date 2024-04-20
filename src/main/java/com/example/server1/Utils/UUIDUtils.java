package com.example.server1.Utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
