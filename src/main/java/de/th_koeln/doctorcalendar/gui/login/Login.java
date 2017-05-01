package de.th_koeln.doctorcalendar.gui.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.th_koeln.doctorcalendar.service.login.LoginService;

@SpringComponent
@VaadinSessionScope
public class Login extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	private Button buttonLogin = new Button("Login");
	private TextField userName = new TextField("Benutzername");
	private PasswordField password = new PasswordField("Passwort");
	private Label loginLabel = new Label("Bitte logge dich ein, um den Kalender benutzen zu können");

	@Autowired
	LoginService loginService;

	@Override
	public void enter(ViewChangeEvent aEvent) {
		addAllComponents();
		setAlignment();
		setSpacing(true);
		buttonLogin.addClickListener(getLoginClickListener());
	}

	private void addAllComponents() {
		addComponent(loginLabel);
		addComponent(userName);
		addComponent(password);
		addComponent(buttonLogin);
	}

	private void setAlignment() {
		setComponentAlignment(loginLabel, Alignment.MIDDLE_CENTER);
		setComponentAlignment(userName, Alignment.MIDDLE_CENTER);
		setComponentAlignment(password, Alignment.MIDDLE_CENTER);
		setComponentAlignment(buttonLogin, Alignment.MIDDLE_CENTER);
	}

	private ClickListener getLoginClickListener() {
		return new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				if (loginService.isCorrectLoginData(userName.getValue(), password.getValue())) {
					System.out.println("Login");
				}
				System.out.println("False");
			}
		};
	}
}