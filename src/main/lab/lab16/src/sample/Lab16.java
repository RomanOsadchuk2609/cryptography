package main.lab.lab16.src.sample;

public class Lab16 {
    public static void main(String[] args) {
        SignatureCenter signatureCenter = new SignatureCenter();
        signatureCenter.addClient("client0", 10);
        signatureCenter.addClient("client1", 31);
        System.out.println(signatureCenter.getClientsInfo());
        System.out.println("\n*******************************************\n");
        String resultLog = signatureCenter.doSignatureOperation("Hello, World!", "id0", "id1");
        System.out.println(resultLog);
    }
}
