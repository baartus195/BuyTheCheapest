package buythecheapest.controller;

import java.util.function.Supplier;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

public class MyRun implements Runnable {

	private final String url;
	private final String email;
	private final String password;
	private final Supplier<Boolean> shouldContinue;
	int nowDo = 0;
	String info1 = "", info2 = "";

	public MyRun(final String url, final String email, final String password, final Supplier<Boolean> shouldContinue) {
		this.url = url;
		this.email = email;
		this.password = password;
		this.shouldContinue = shouldContinue;
	}

	@Override
	public void run() {
		SendAnEmail send = new SendAnEmail(email, password);
		send.test();
		WebsiteInfo websiteInfo = new WebsiteInfo(url);
		while (this.shouldContinue.get()) {
			try {
				info1 = websiteInfo.chceckOutWebsite();
				System.out.println("Info1: " + info1); //line for test, you can delete 
				if (nowDo == 1) {
					if (!(info1.equals(info2))) {
						send.setContent(url);
						try {
							send.send();
						} catch (MessagingException e) {
							System.out.println("Incorrect adrress or password");
							e.printStackTrace();
						}
					}
				}
				Thread.sleep(50000);
				info2 = websiteInfo.chceckOutWebsite();
				System.out.println("Info2: " + info2); //line for test, you can delete 
				nowDo = 1;
				if (!(info1.equals(info2))) {
					// SendAnEmail send=new SendAnEmail(email,password);
					send.setContent(url);
					try {
						send.send();
					} catch (MessagingException e) {
						JOptionPane.showMessageDialog(null, "Incorrect adrress!");
						e.printStackTrace();
					}
				}
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "Incorrect URL!");
				e.printStackTrace();

			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Incorrect URL!");
				e.printStackTrace();
			}

		}
	}// end run

}// end MyRun
