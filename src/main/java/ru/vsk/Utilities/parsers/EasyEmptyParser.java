package ru.vsk.Utilities.parsers;

public class EasyEmptyParser extends EasyParser{
    private String data;

    public EasyEmptyParser(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
