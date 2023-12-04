package com.minisun.todolist.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.minisun.todolist.configuration.WebSecurityConfig;
import com.minisun.todolist.dto.TodoRequestDTO;
import com.minisun.todolist.dto.UserRequestDTO;
import com.minisun.todolist.entity.User;
import com.minisun.todolist.security.UserDetailsImpl;
import com.minisun.todolist.service.UserService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(
        controllers = {UserController.class, TodoController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
public class ControllerTest {
    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }

    private void mockUserSetup() {
        // Mock유저 생성
        String username = "username1";
        String password = "password1";
        User testUser = new User(username, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("로그인 Page")
    void test1() throws Exception {
        // when - then
        mvc.perform(get("/api/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원 가입 요청 처리")
    void test2() throws Exception {
        // given
        String username = "username1";
        String password = "password1";
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setPassword(password);
        userRequestDTO.setUsername(username);

        String jsonInString = new Gson().toJson(userRequestDTO);


        // when - then
        mvc.perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInString)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/api/user/login-page"))
                .andDo(print());
    }

    @Test
    @DisplayName("신규 Todo 등록")
    void test3() throws Exception {
        // given
        this.mockUserSetup();
        String title = "Todo1";
        String content = "Content1";
        TodoRequestDTO requestDto = new TodoRequestDTO(
                title,
                content
        );

        String postInfo = objectMapper.writeValueAsString(requestDto);

        // when - then
        mvc.perform(post("/api/todos")
                        .content(postInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}
