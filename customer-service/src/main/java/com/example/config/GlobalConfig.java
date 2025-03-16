package com.example.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
/*
'@ConfigurationProperties(..)': Cette annotation indique que la classe GlobalConfig contient des propriétés de configuration qui peuvent être liées à
    des valeurs définies dans le fichier de configuration (typiquement application.properties).
    Le préfixe spécifié ici (global.params) signifie que toutes les propriétés de cette classe seront mappées à des propriétés qui commencent par ce préfixe.

'GlobalConfig': C'est une classe de configuration qui sert à encapsuler des paramètres globaux pour l'application. En utilisant
    les annotations mentionnées, Spring Boot peut injecter les valeurs
    à partir du fichier de configuration directement dans cette classe.
 */
@ConfigurationProperties(prefix = "global.params")
@Getter @Setter @ToString
public class GlobalConfig {
    private int p1;
    private int p2;
}
