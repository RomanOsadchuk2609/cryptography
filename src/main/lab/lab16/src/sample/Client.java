package main.lab.lab16.src.sample;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Client {
    private String id;
    private String name;
    private int key;
    private BiFunction<String, Integer, String> cryptoFunction;
    private Function<String, Integer> hashFunction;

    public Client(String id, String name, int key, BiFunction<String, Integer, String> cryptoFunction, Function<String, Integer> hashFunction) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.cryptoFunction = cryptoFunction;
        this.hashFunction = hashFunction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BiFunction<String, Integer, String> getCryptoFunction() {
        return cryptoFunction;
    }

    public void setCryptoFunction(BiFunction<String, Integer, String> cryptoFunction) {
        this.cryptoFunction = cryptoFunction;
    }

    public Function<String, Integer> getHashFunction() {
        return hashFunction;
    }

    public void setHashFunction(Function<String, Integer> hashFunction) {
        this.hashFunction = hashFunction;
    }

    @Override
    public String toString() {
        return '{' +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", key=" + key +
                '}';
    }
}
