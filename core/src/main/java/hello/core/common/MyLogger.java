package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request") //HTTP 요청 당 빈이 하나씩 생성
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) { //빈이 생성되는 시점에 알 수 없어 외부의 setter로 후에 입력받음
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println();
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }

}
