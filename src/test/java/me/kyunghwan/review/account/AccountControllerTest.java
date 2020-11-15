package me.kyunghwan.review.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.List;
import me.kyunghwan.review.BaseControllerTest;
import me.kyunghwan.review.account.dto.AccountRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends BaseControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("POST /api/accounts Succes")
    @Test
    void test() throws Exception {
        AccountRequestDto requestDto = AccountRequestDto.builder()
                .email(email)
                .password(password)
                .myGenres(List.of("SF", "느와르"))
                .build();

        mockMvc.perform(post("/api/accounts")
                    .content(objectMapper.writeValueAsString(requestDto))
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
        ;
    }

}