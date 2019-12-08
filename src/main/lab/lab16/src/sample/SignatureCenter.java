package main.lab.lab16.src.sample;

import main.util.CryptographyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SignatureCenter {
    private static final String LINE_DELIMITER = "\n";
    private List<Client> clients = new ArrayList<>();
    private BiFunction<String, Integer, String> cryptoFunction = CryptographyUtils::xOrText;
    private Function<String, Integer> hashFunction = String::hashCode;

    public String doSignatureOperation(String message, String senderId, String receiverId) {
        StringBuilder log = new StringBuilder();

        //Clients validation
        Client clientA = getClientById(senderId);
        if (clientA == null) {
            log.append("There is no any client with id: ").append(senderId);
            return log.toString();
        } else {
            log.append("ClientA: ").append(clientA).append(LINE_DELIMITER);
        }

        Client clientB = getClientById(receiverId);
        if (clientB == null) {
            log.append("There is no any client with id: ").append(receiverId);
            return log.toString();
        } else {
            log.append("ClientB: ").append(clientB).append(LINE_DELIMITER);
            log.append(LINE_DELIMITER);
        }

        //ClientA -> Signature Center
        String encryptedMessageA = clientA.getCryptoFunction().apply(message, clientA.getKey());
        log.append("ClientA -> Signature Center:").append(LINE_DELIMITER);
        log.append("idA: ").append(senderId).append(LINE_DELIMITER);
        log.append("idB: ").append(receiverId).append(LINE_DELIMITER);
        log.append("Encrypted Message: ").append(encryptedMessageA).append(LINE_DELIMITER);
        Integer hashA = clientA.getHashFunction().apply(message);
        log.append("HashA: ").append(hashA).append(LINE_DELIMITER);
        log.append(LINE_DELIMITER);

        //Signature Center
        log.append("Signature Center:").append(LINE_DELIMITER);
        Integer keyA = clientA.getKey();
        log.append("KeyA: ").append(keyA).append(LINE_DELIMITER);
        String decryptedMessageOnKeyA = this.cryptoFunction.apply(encryptedMessageA, keyA);
        log.append("Decrypted message on keyA: ").append(decryptedMessageOnKeyA).append(LINE_DELIMITER);
        Integer hashSC = this.hashFunction.apply(decryptedMessageOnKeyA);
        log.append("HashSC: ").append(hashSC).append(LINE_DELIMITER);
        boolean hashAEqualsHashSC = hashA.equals(hashSC);
        if (hashAEqualsHashSC) {
            log.append("HashA = HashSC").append(LINE_DELIMITER);
            log.append(LINE_DELIMITER);

            //Signature Center -> ClientB
            log.append("Signature Center -> ClientB:").append(LINE_DELIMITER);
            log.append("idA: ").append(senderId).append(LINE_DELIMITER);
            Integer keyB = clientB.getKey();
            log.append("KeyB: ").append(keyB).append(LINE_DELIMITER);
            String encryptedMessageOnKeyB = this.cryptoFunction.apply(decryptedMessageOnKeyA, keyB);
            log.append("Encrypted Message on keyB: ").append(encryptedMessageOnKeyB).append(LINE_DELIMITER);
            log.append("HashA: ").append(hashA).append(LINE_DELIMITER);
            log.append(LINE_DELIMITER);

            //ClientB
            log.append("ClientB:").append(LINE_DELIMITER);
            String decryptedMessageB = clientB.getCryptoFunction().apply(encryptedMessageOnKeyB, clientB.getKey());
            log.append("DecryptedMessage: ").append(decryptedMessageB).append(LINE_DELIMITER);
            Integer hashB = clientB.getHashFunction().apply(decryptedMessageB);
            log.append("HashB: ").append(hashB).append(LINE_DELIMITER);
            boolean hashAEqualsHashB = hashA.equals(hashB);
            if (hashAEqualsHashB) {
                log.append("HashA = HashB").append(LINE_DELIMITER);
                log.append("Message successfully signed!");
            } else {
                log.append("HashA != HashB").append(LINE_DELIMITER);
            }
        } else {
            log.append("HashSC != HashA");
        }
        return log.toString();
    }

    public void addClient(String name, int key) {
        clients.add(new Client(getNextClientId(), name, key, cryptoFunction, hashFunction));
    }

    public String getClientsInfo() {
        StringBuilder clientsInfo = new StringBuilder("Clients: ");
        clientsInfo.append(LINE_DELIMITER)
                .append(clients.stream()
                        .map(Client::toString)
                        .collect(Collectors.joining(LINE_DELIMITER))
                );
        return clientsInfo.toString();
    }

    private Client getClientById(String id) {
        return clients.stream()
                .filter(client -> id.equals(client.getId()))
                .findFirst()
                .orElse(null);
    }

    private String getNextClientId() {
        return "id" + clients.size();
    }

}
