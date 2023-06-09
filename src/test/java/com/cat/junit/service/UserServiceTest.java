package com.cat.junit.service;

import com.cat.junit.dao.UserDao;
import com.cat.junit.entity.User;
import com.cat.junit.extension.ConditionalExecutionExtension;
import com.cat.junit.extension.ExceptionHandlingExtension;
import com.cat.junit.extension.LifeCycleExtension;
import com.cat.junit.extension.PostProcessingExtension;
import lombok.experimental.StandardException;
import net.bytebuddy.asm.Advice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@Tag("userService")
@Tag("user")
@Tag("fast")
@ExtendWith(
        {
                LifeCycleExtension.class,
                PostProcessingExtension.class,
                ConditionalExecutionExtension.class,
                MockitoExtension.class
//                ExceptionHandlingExtension.class
        }
)
public class UserServiceTest {
    private static final User EXISTING_USER = User.builder()
            .name("name")
            .password("password")
            .id("1")
            .build();

    @Mock
//            (strictness = Mock.Strictness.LENIENT)
    private UserDao userDao;
    @InjectMocks
    private UserService userService;
    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;

    @BeforeAll
    static void setSomePropertiesForAllTests() {
        System.out.println("Before all:");
    }

    @BeforeEach
    void setUserService() {
        System.out.println("Before Each:" + this);
//        this.userDao = Mockito.mock(UserDao.class);
//        this.userDao = Mockito.spy(UserDao.getInstance());
//        this.userService = new UserService(userDao);

    }


//@Test
//void bddcheck() {
//        BDDMockito.given(userDao.delete(1))
//                .willReturn(true);
//}

    @Test
    void shouldDeleteExistingUser() {
        int intId = Integer.parseInt(EXISTING_USER.getId());

        Mockito.doReturn(true).when(userDao).delete(intId);
        userService.addUser(EXISTING_USER);

//        ArgumentCaptor<Integer> stringArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        assertThat(userService.delete(EXISTING_USER.getId())).isTrue();

        Mockito.verify(userDao, Mockito.times(1)).delete(integerArgumentCaptor.capture());
        assertThat(integerArgumentCaptor.getValue()).isEqualTo(1);
    }

    @Test
    void falseIfDeleteNoExistingUser() {
        Mockito.doReturn(false).when(userDao).delete(2);
        assertThat(userService.delete("2")).isFalse();
    }

//    @Test
//    @Tag("login")
//    void throwIllegalArgumentExceptionIfUserNameOrPasswordIsNull() {
//        assertAll(
//                () -> assertThrows(IllegalArgumentException.class, () -> userService.login("name", null), "password null"),
//                () -> assertThrows(IllegalArgumentException.class, () -> userService.login(null, "password"), "name null")
//        );
//    }

    @ParameterizedTest(name = "test {index} --- {arguments}")
//    @CsvFileSource(resources = "/login-test-data.csv", numLinesToSkip = 1)
//            @CsvSource({
//                    "name,password",
//                    "name1,password1"
//            })
    @MethodSource("com.cat.junit.service.UserServiceTest#getArgumentsForLoginTest")
    void loginParametrizedTest(String login, String password, Optional<User> user, String message) {
        userService.addUser(EXISTING_USER);

        assertThat(userService.login(login, password))
                .as(message)
                .isEqualTo(user);
    }

    static Stream<Arguments> getArgumentsForLoginTest() {
        return Stream.of(
                Arguments.of("name", null, Optional.empty(), "password null"),
                Arguments.of(null, "password", Optional.empty(), "login null"),
                Arguments.of("name", "password", Optional.of(EXISTING_USER), "existing user"),
                Arguments.of("", "password", Optional.empty(), "login is empty"),
                Arguments.of("name", "", Optional.empty(), "password is empty")
        );
    }



    @AfterEach
    void someCleanActions() {
        System.out.println("AfterEach:" + this);
    }

    @AfterAll
    static void someCleanActionsForAllTests() {
        System.out.println("After all:");
    }

    @Nested
    @Tag("getAll")
    @DisplayName("get all users method's tests")
    class GetAllTest {
        @Test
//        @Disabled("I am not working correctly. Fix me, please )")
//        @Timeout(value = 150, unit = TimeUnit.MILLISECONDS)
        void emptyListIfNoUsers() {
            assertTimeout(Duration.ofMillis(170), () -> {
                System.out.println("EmptyListIfNoUsers:" + this);
                List<User> userList = userService.getAll();
                assertThat(userList)
                        .as("Have to be empty")
                        .isEmpty();
            });


//        assertTrue(userList.isEmpty(), "Have to be empty");
        }

//        @Test
        @RepeatedTest(4)
        void sizeIfUsersAdded(RepetitionInfo repetitionInfo) {
            System.out.println(repetitionInfo.getCurrentRepetition() + ") SizeIfUsersAdded: " + this);
            userService.addUser(new User());
            userService.addUser(new User());
            List<User> userList = userService.getAll();
//        int userListSize = userList.size();

            assertThat(userList)
                    .as("Have to be 2")
                    .hasSize(2);
//        assertEquals(2, userListSize, "Have to be 2");
        }
    }
}
