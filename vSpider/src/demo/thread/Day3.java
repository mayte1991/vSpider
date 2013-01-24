package demo.thread;

public class Day3 {

	public static void main(String[] args) {
		Res r = new Res();
		
		new Thread(new Input(r)).start();
		new Thread(new Output(r)).start();
	}
}

class Input implements Runnable {
	
	private Res r;
	private int sexInt = 1;
	
	public Input(Res r) {
		this.r = r;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(r) {
				sexInt = sexInt%2 +1;
				if(sexInt == 1) {
					//��
					r.name = "��С˧";
					r.sex = "��";
				}else {
					r.name="��С��";
					r.sex="Ů";
				}
				
				try {
					r.notify();
					r.wait();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}

class Output implements Runnable {
	private Res r;
	
	public Output(Res r) {
		this.r = r;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(r) {
				
				System.out.printf("�̣߳�%s, %s\n",Thread.currentThread().getName(), r);
				
				try {
					r.notify();
					r.wait();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Res {
	public String name;
	public String sex;
	@Override
	public String toString() {
		return "Res [name=" + name + ", sex=" + sex + "]";
	}
	
	
}