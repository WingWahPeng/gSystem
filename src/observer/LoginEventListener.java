package observer;

import java.util.EventListener;

public interface LoginEventListener extends EventListener{
	public void validateLogin(LoginEvent event);//������Ӧ����
}
