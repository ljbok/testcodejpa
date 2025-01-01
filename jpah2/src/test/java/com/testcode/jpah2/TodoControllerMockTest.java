package com.testcode.jpah2;

import com.testcode.jpah2.todo.controller.TodoController;
import com.testcode.jpah2.todo.dto.ResponseTodoDto;
import com.testcode.jpah2.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// static import
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

// 단위 테스트 : 가짜 서비스 객체 빈 사용 -> 즉, 컨트롤러 계층까지만 테스트 가능하다는 얘기
@ExtendWith(SpringExtension.class) // junit 5 일 경우 vs junut 4 일 경우 @RunWith(SpringRunner.class)
@WebMvcTest(value = TodoController.class)
public class TodoControllerMockTest {
    // log
    private static final Logger log = LoggerFactory.getLogger(TodoControllerMockTest.class); // 테스트 유형 : 단위 테스트

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void findAllTodoTest() throws Exception {

        // GIVEN
        List<ResponseTodoDto> mockResponse = new ArrayList<ResponseTodoDto>();

        // CASE 1
        // 예상 결과 (JSON 형식의 문자열 대비 객체)
        String mockDateStr = "2025-01-01 22:46:40.138806";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        ResponseTodoDto mockResponseTodo = new ResponseTodoDto(1L,
                                                            "첫 번째 todo 입력",
                                                            LocalDateTime.parse(mockDateStr, formatter),
                                                     false);
        mockResponse.add(mockResponseTodo);

        // CASE 2
        // String mockResponseTodo = "첫 번째 todo 입력"; // 예상 결과 (이미 DB 에 저장된 값)
        // mockResponse.add(mockResponseTodo);


        String expected = "[{" +
                "\"id\": 1," +
                "\"title\": \"첫 번째 todo 입력\"," +
                "\"date\": \"2025-01-01T22:46:40.138806\"," +
                "\"isFinish\": false" +
                "}]"; // 예상 결과 (JSON 형식의 문자열)

        // WHEN
        // 가짜 빈 todoService 정의
        // test 클래스에서 controller mapping 되는 api 요청을 보낼 때 해당 controller 메소드에서
        // 사용되는 service 의 메소드를 여기서 정의하고, 정의한 서비스 로직이 수행되게 한다.
        // 쉽게 예시 들자면 service 메소드 인터셉트 해서 재정의 후 재정의한 메소드 실행시키는 것.
        /*
        // 타겟 서비스 메소드
         @GetMapping("/findAll")
         public List<ResponseTodoDto> findAllTodo() {
            return todoService.findAllTodo();
         }
        */
        when(todoService.findAllTodo()).thenReturn(mockResponse); // thenReturn 안에 데이터 타입은 실제 TodoService.findAllTodo() 의 반환 데이터 타입과 동일 해야함

        // THEN
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/todo/findAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // log.info(expected);
        // log.info(result.getResponse().getContentAsString());

        // CASE 1
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        // CASE 2
        // assertEquals(expected, result.getResponse().getContentAsString());
    }

    // 조희 - 실패할
    @Test
    public void findAllTodoTestFailed() throws Exception {

        // GIVEN
        List<ResponseTodoDto> mockResponse = new ArrayList<ResponseTodoDto>();

        // CASE 1
        // 예상 결과 (JSON 형식의 문자열 대비 객체)
        String mockDateStr = "2025-01-01 22:46:40.138806";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        ResponseTodoDto mockResponseTodo = new ResponseTodoDto(1L,
                "첫 번째 todo 입력",
                LocalDateTime.parse(mockDateStr, formatter),
                false);
        mockResponse.add(mockResponseTodo);

        // CASE 2
        // String mockResponseTodo = "첫 번째 todo 입력"; // 예상 결과 (이미 DB 에 저장된 값)
        // mockResponse.add(mockResponseTodo);


        String expected = "[{" +
                "\"id\": 1," +
                "\"title\": \"존재하지 않은 실패할 todo\"," +
                "\"date\": \"2025-01-01T22:46:40.138806\"," +
                "\"isFinish\": false" +
                "}]"; // 예상 결과 (JSON 형식의 문자열)

        // WHEN
        // 가짜 빈 todoService 정의
        // test 클래스에서 controller mapping 되는 api 요청을 보낼 때 해당 controller 메소드에서
        // 사용되는 service 의 메소드를 여기서 정의하고, 정의한 서비스 로직이 수행되게 한다.
        // 쉽게 예시 들자면 service 메소드 인터셉트 해서 재정의 후 재정의한 메소드 실행시키는 것.
        /*
        // 타겟 서비스 메소드
         @GetMapping("/findAll")
         public List<ResponseTodoDto> findAllTodo() {
            return todoService.findAllTodo();
         }
        */
        when(todoService.findAllTodo()).thenReturn(mockResponse); // thenReturn 안에 데이터 타입은 실제 TodoService.findAllTodo() 의 반환 데이터 타입과 동일 해야함

        // THEN
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/todo/findAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // log.info(expected);
        // log.info(result.getResponse().getContentAsString());

        // CASE 1
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        // CASE 2
        // assertEquals(expected, result.getResponse().getContentAsString());
    }

}
