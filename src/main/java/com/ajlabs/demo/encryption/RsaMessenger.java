package com.ajlabs.demo.encryption;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaMessenger {

    private final RsaEncryptor encryptionHandler;
    private final String personId;

    public RsaMessenger( String personId) {
        this.encryptionHandler = new RsaEncryptor();
        this.personId = personId;
    }

    public PublicKey getPublicKey() {
        return this.encryptionHandler.getPublicKey();
    }


    public PrivateKey getPrivateKey() {
        return this.encryptionHandler.getPrivateKey();
    }

    private boolean decryptedMessageHashIsValid(String decryptedMessage, String hashedMessage) {
        String decryptedMessageHashed = this.encryptionHandler.hashMessage(decryptedMessage);
        if(decryptedMessageHashed.equals(hashedMessage)) {
            return true;
        }

        return false;
    }
}
