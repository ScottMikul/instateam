package com.scotty.thymeleafplayground;

import java.util.ArrayList;

/**
 * Created by scott on 5/20/2017.
 */
public class ClientWithSelectionListWrapper {

    public ArrayList<String> getC() {
        return c;
    }

    public void setC(ArrayList<String> c) {
        this.c = c;
    }

    private ArrayList<ClientWithSelection> clientList;
    private ArrayList<String> c;

    public ArrayList<ClientWithSelection> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<ClientWithSelection> clients) {
        this.clientList = clients;
    }
}