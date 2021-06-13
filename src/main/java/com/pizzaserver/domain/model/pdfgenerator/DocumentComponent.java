package com.pizzaserver.domain.model.pdfgenerator;

import com.pizzaserver.domain.dto.UserDataDto;

public interface DocumentComponent {
    void createDocument(UserDataDto userDataDto, String fileDestination);
}
