package GUi;

//�ڹ� AWT ����

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;


public class Calculators extends Frame implements ActionListener  {
    private static final String String = null;
	public TextField input;  //TextField ���� ����
    public Button bt; //��ư  bt ����
    public boolean state = true; //true ���ڸ� false�� ���ڸ� 
    public int op; //������ ����
    public double buffer; //���� ����
    public boolean opstate= true; // ������ ��ư���� ����(���� ���°� True) // ������ + ���ϰ��
    public double saveop;
    ArrayList<ResultSave> rss;
    
class ResultSave{ //��� �����
    	public String _cals;
    	public double _result;  	
    	
    	public void save_result(double results){
    		_result = results;
    	}
    }


public static void main(String[] args) {
   Calculators cal = new Calculators();  
} 
Calculators(){ //�̺�Ʈ �ڵ鷯
    
    super("����"); // �̸����� ��ɾ�
    Panel main = new Panel(); //�г�  main ���� - Input��
    main.setLayout(new BorderLayout()); // �⺻ ������Ʈ Ʋ ����
    input = new TextField(); //TextField ���� input�� ����
    main.add("North", input); //input�� ���� �гο� ��ġ
    Panel p = new Panel(); //�г� P ����
    p.setLayout(new GridLayout(5, 4)); // ���̺� 4�� 4�� ���� (p �г�)
    //�г� p ���� 4,4�� ����

    rss = new ArrayList<ResultSave>();
    //��ư ������
    String s = "789+456-123*0C=/";
    for(int i=0; i<s.length(); i++)
    {
        bt = new Button(s.substring(i, i+1)); //���ۺ��� ������
        p.add(bt);  //Panel ���ٰ� button(bt)���� �ִ´�.
        bt.addActionListener(this); //��ư �̺�Ʈ �߻�
    }
    String s1 = "����";
    {
        bt = new Button(s1); //���ۺ��� ������
        p.add(bt);  //Panel ���ٰ� button(bt)���� �ִ´�.
        bt.addActionListener(this); //��ư �̺�Ʈ �߻�
    }
    String s2 = "�ε�";
    {
        bt = new Button(s2); //���ۺ��� ������
        p.add(bt);  //Panel ���ٰ� button(bt)���� �ִ´�.
        bt.addActionListener(this); //��ư �̺�Ʈ �߻�
    }
    String s3 = "���갡��";
    {
        bt = new Button(s3); //���ۺ��� ������
        p.add(bt);  //Panel ���ٰ� button(bt)���� �ִ´�.
        bt.addActionListener(this); //��ư �̺�Ʈ �߻�
        //Single.instance.start = true;
    }
    String s4 = "����Ұ���";
    {
        bt = new Button(s4); //���ۺ��� ������
        p.add(bt);  //Panel ���ٰ� button(bt)���� �ִ´�.
        bt.addActionListener(this); //��ư �̺�Ʈ �߻�
        //Single.instance.start = false;
    }
    //��ư��������
    
    main.add("Center", p); //panel( p )�� �߾ӿ� ����
    add(main);
    
    setSize(300 ,300); //������
    show();  //ȭ�� ��û
       
    //�����ư -> �ݱ� ��ư ������ �ڵ� ����
    addWindowListener(new WindowAdapter() {
    	//������ �̺�Ʈ  -> �ݱ� ��ư Ŭ���� -> WindowClosing�� ����ȴ�.
    	//Adpater ��쿡�� �ƹ��� ���� ���� Ŭ���� -> �ؿ� Closing �޼ҵ带 ����ϱ� ���Ͽ�
        public void windowClosing(WindowEvent we) { //���� ��ư �޼ҵ� (�ݱ⸦ ������ �Ǹ� ����)
            System.exit(0); //�ý��� ����
        }
        });   
}
	//ActionListener�� ��ӹ��� �޼ҵ� ActionPerformed �̴�.
	public void actionPerformed(ActionEvent ae) { //ActionEvent ����� ���ؼ� ���
		//�Ϲ� ��ư Ŭ���� �̺�Ʈ�� ����ȴ�.
		// �߻��� �̺�Ʈ��  val���� �ִ´�(String������ ��ȯ)
		String val = ae.getActionCommand();
		System.out.println(val + "val");
		if ( !(val.equals("����") || val.equals("�ε�")||val.equals("���갡��")||val.equals("����Ұ���")))
		{
			//���ڿ��� ��ȯ�ؾ߸� �ؿ��� ������ C���� ���ڳ� 0~9���ڸ� ���� ���� �ִ�.
			if(val.equals("C")){ //C���� �ް� �Ǹ�  ""��� , �� ����
				input.setText(""); //if���� ���̸� ���� //setText�� �ؽ�Ʈ �ʵ忡�� �ؽ�Ʈ�� ����
				buffer = 0;    // ���ۿ�  0 ���� �ִ´�.
			}
			else if('0'<=val.charAt(0) && val.charAt(0) <= '9')
			{  //���� ���� Ȯ�� ( 0 ~ 9 )
				if(state){
					input.setText(val);

					System.out.println(val + "cal");
				}
				else{ 
					input.setText(input.getText()+val); //�ԷµȰ��� ��ư
				}
				state = false; 
			}
			else {
				if(singleT.getInstance().start){
					Operation(val); //Operation �Լ��� val ���� �Ѱ���
					//System.out.println(val + " op");
				}
				else{
					System.out.println(val + " xxxxxxxxx");
				}
			}
		}
		else
		{
			if(val.equals("����")){
				saveop = Double.valueOf(input.getText()); //(new Double(input.getText())).doubleValue();
				
				rss.add(new ResultSave());
				rss.get(rss.size()-1).save_result(saveop);
				
			}
			else if(val.equals("�ε�")){
				for(int k = 0; k < rss.size(); k++){
					System.out.println(k + " = " + rss.get(k)._result);
				}
			}
			if(val.equals("���갡��")){
				//result();
				singleT.getInstance().start = true;
			}
			else if(val.equals("����Ұ���")){
				System.out.println("���� �Ұ����մϴ�!");
				singleT.getInstance().start = false;
			}
		}
	}
	//���� ó���ϴ� �޼ҵ� Operation
	public void Operation(String val) {
	    if(val.equals("+")){
	        op = 1;  //Switch�� ����ϱ� ���ؼ� ���
	        if(opstate) result();  //opstate�� ������ ��ư ���� ���� Ȯ��!
	        opstate = true;	       
	    }else if(val.equals("-")){
	        op = 2;
	        if(opstate) result();
	        opstate = true;
	    }else if(val.equals("*")){
	        op = 3;
	        if(opstate) result();
	        opstate = true;
	    }else if(val.equals("/")){
	        op = 4;
	        if(opstate) result();
	        opstate = true;
	    }else {
	        result();
	    }
	    buffer = (new Double(input.getText())).doubleValue(); //Double������ �����. -> �Էµ� ���� ���ۿ� ����
	    state = true; //���� �ϳ���� ǥ�� true�� ���ؼ�
	    }

	public void result() {  //result ����
        double result = 0; //result �ʱ�ȭ
        double in = (new Double(input.getText())).doubleValue(); //�Է¹��� ���� double������ ����
        switch(op){  //���갪�� ���� switch���� ����ϱ� ���ؼ� op������ �����..
        case 1:
            result = buffer+in;      
            break;
        case 2:
            result = buffer-in;       
            break;
        case 3:
            result = buffer*in;       
            break;
        case 4:
            result = buffer/in;
            break;
        }
        input.setText(result+""); //���� �����
        opstate = false;  
	  }

}	
  
   


/*ResultSave save = new ResultSave();
 save.save_result(result); //��� ���� ResultSave -> save_result�� ����
*/
    
/*
ResultSave rs1 = new ResultSave();
rs1.save_result("cals fx1", buffer);

ResultSave rs2 = new ResultSave();
rs2.save_result("cals fx2", in);

ResultSave rs3 = new ResultSave();
rs3.save_result("cals fx3", result);

System.out.println("ó���� : " + rs1._cals + "=" + rs1._result);
System.out.println("��°�� : " + rs2._cals + "=" + rs2._result);
System.out.println("�հ谪 : " + rs3._cals + "=" + rs3._result);
*/



