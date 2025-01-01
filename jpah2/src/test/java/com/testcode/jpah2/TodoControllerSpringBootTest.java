package com.testcode.jpah2;

//import com.testcode.jpah2.todo.repository.TodoRepository;
//import com.testcode.jpah2.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// static import
import static org.assertj.core.api.Assertions.*;

// 통합 테스트 : 실제 서비스 객체 빈 사용 -> 즉, 전체 로직 테스트 가능
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Jpah2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerSpringBootTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    // 빈 주입 따로 안 해줘도 @SpringBootTest 로 테스트 하면 전체 컨텍스트 빈이 주입되므로 따로
    // @Autowired. @Injects, 생성자 로 별도로 타겟 빈 주입을 수행할 필요가 없다.
    // @Autowired
    // private TodoService todoService;

    // @Autowired
    // private TodoRepository todoRepository;

    // 조희 - 성공할
    @Test
    public void findAllTodoTest() throws Exception {
        // GIVEN
        /*
        // CASE 1
        String expected = "[{" +
            "\"id\": 1," +
            "\"title\": \"첫 번째 todo 입력"," +
//            "\"date\": \"2025-01-01 22:46:40.138806\"," +
            "\"isFinish\": false" +
            "}]"; // 예상 결과 (JSON 형식의 문자열)
         */

        // CASE 2
        String expected = "첫 번째 todo 입력"; // 예상 결과 (이미 DB 에 저장된 값)

        // WHEN 은 따로 없음 : service 클래스를 MockBean 이 아닌 실제 클래스로 사용할 것이기 떄문
        // THEN
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/todo/findAll", String.class);

        // CASE 1
        // JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        // CASE 2
        assertThat(response.getBody()).contains(expected);
    }

    // 조희 - 실패할
    @Test
    public void findAllTodoTestFailed() throws Exception {
        // GIVEN
        String expected = "존재하지 않은 실패할 todo"; // 예상 결과 (이미 DB 에 저장된 값)

        // WHEN 은 따로 없음 : service 클래스를 MockBean 이 아닌 실제 클래스로 사용할 것이기 떄문
        // THEN
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/todo/findAll", String.class);

        // CASE 1
        // JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        // CASE 2
        assertThat(response.getBody()).contains(expected);
    }
}
