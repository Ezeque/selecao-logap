package com.logap.backend.Service;

import org.springframework.stereotype.Service;

@Service
public class VogalService {

    public String encontrarVogal(String frase) {
        if (frase == null || frase.isEmpty()) {
            return null;
        }

        char[] chars = frase.toCharArray();
        boolean achouAlvo = false;

        for (int i = 2; i < chars.length; i++) {
            char anterior = chars[i - 1];
            char anteriorDois = chars[i - 2];
            char atual = chars[i];
            if (isVogal(atual) && !isVogal(anterior) && isVogal(anteriorDois) && !isRepetida(atual, frase)) {
                achouAlvo = true;
            }

            if (achouAlvo) {
                return String.valueOf(atual);
            }
        }

        return null;
    }

    private boolean isVogal(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    private boolean isRepetida(char c, String frase) {
        int count = 0;
        for (char ch : frase.toCharArray()) {
            if (ch == c) {
                count++;
            }
        }
        return count > 1;
    }
}
