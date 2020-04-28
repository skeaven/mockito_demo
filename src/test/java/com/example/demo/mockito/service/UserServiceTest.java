package com.example.demo.mockito.service;

import com.example.demo.mockito.utils.AopTargetUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BaseService baseService;

    @Mock
    private BaseService spyBaseService;

    @BeforeEach
    public void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(AopTargetUtils.getTarget(userService), "baseService", spyBaseService);

        // 定义你需要的模拟的行为
        when(spyBaseService.findFromDb())
                .thenReturn("db_data_custom")  // 第一种情况
                .thenReturn("next");  // 第二种情况
    }

    @AfterEach
    public void clearMocks() throws Exception {
        ReflectionTestUtils.setField(AopTargetUtils.getTarget(userService), "baseService", baseService);
    }

    @Test
    public void should_success_when_testUserService() {

        String result = userService.test();
        Assertions.assertEquals("db_data_custom-test", result);

        result = userService.test();
        Assertions.assertEquals("next-test", result);

    }
}
