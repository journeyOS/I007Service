package com.journeyOS.i007Service.interfaces;

import android.os.IBinder;

interface IServiceFetcher {
    IBinder getService(String name);
    void addService(String name, IBinder service);
    void removeService(String name);
}
