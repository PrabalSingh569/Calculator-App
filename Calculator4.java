import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Calculator4 
{
	JFrame frame=new JFrame("Calculator");
	JTextField textbox=new JTextField("0");
	JPanel panel=new JPanel();
	JButton[] buttons=new JButton[20];
	public Calculator4()
	{
		frame.setSize(400,440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		addTextBox();
		frame.setVisible(true);
	}
	private void addTextBox()
	{
		textbox.setBounds(20,20,360,40);
		frame.add(textbox);
		textbox.setFont(new Font("arial",Font.PLAIN,25));
		textbox.setHorizontalAlignment(JTextField.RIGHT);
		textbox.setEditable(false);
		textbox.setBackground(Color.white);
		textbox.setBorder(BorderFactory.createLineBorder(Color.black,1));
		addPanel();
	}
	private void addPanel()
	{
		panel.setBounds(20,90,360,300);
		frame.add(panel);
		//panel.setBackground(Color.black);
		addButtons();
	}
	private void addButtons()
	{
		panel.setLayout(new GridLayout(5,4,5,5));
		Font font=new Font("arial",Font.PLAIN,20);
		String[] str= {"Back","CE","C","%","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
		CalListener listener=new CalListener();
		for(int i=0;i<20;i++)
		{
			buttons[i]=new JButton(str[i]);
			buttons[i].addActionListener(listener);
			buttons[i].setFont(font);
			panel.add(buttons[i]);
			if(i==3||i==7||i==11||i==15||i==19)
			{
				buttons[i].setForeground(Color.red);
			}
			else
			{
				buttons[i].setForeground(Color.blue);
			}
		}
		buttons[17].setFont(new Font("elephant",Font.PLAIN,25));
	}
	class CalListener implements ActionListener
	{
		private int ab=0;
		private float num1;
		private String op=null;
		public void actionPerformed(ActionEvent evt)
		{
			JButton bb=(JButton)evt.getSource();
			//When . button will be clicked 
			if(bb==buttons[17])
			{
				String str=textbox.getText();
				int i=str.indexOf('.');
				if(i!=-1)
					return;
			}
			//When arithmetic symbol button clicked
			if(bb==buttons[7]||bb==buttons[11]||bb==buttons[15]||bb==buttons[19]) 
			{
				if(op!=null)
					cal();
				ab=1;
				num1=Float.parseFloat(textbox.getText());
				op=bb.getText();
			}
			//When digit button clicked
			if(bb==buttons[4]||bb==buttons[5]||bb==buttons[6]||bb==buttons[8]||bb==buttons[9]||bb==buttons[10]||bb==buttons[12]||bb==buttons[13]||bb==buttons[14]||bb==buttons[16]||bb==buttons[17])
			{
				String buttonText=bb.getText();
				String textboxText=textbox.getText();
				if(textboxText.equals("0") || ab==1)
				{
					textbox.setText(buttonText);
					ab=0;
				}
				else
					textbox.setText(textboxText+buttonText);
			}
			//When button C will be clicked 
			if(bb==buttons[2])
			{
				textbox.setText("0");
				ab=0;
				op=null;
			}
			//When button CE will be clicked 
			if(bb==buttons[1])
			{
				textbox.setText("0");
			}
			//When button back will be clicked
			if(bb==buttons[0])
			{
				int num=Integer.parseInt(textbox.getText());
				if(num==0)
					return;
				num=num/10;
				textbox.setText(String.valueOf(num));
			}
			//When = button will be clicked
			if(bb==buttons[18])
			{
				cal();
			}
		}//end of actionPerformed() method
		private void cal()
		{
			float num2=Float.parseFloat(textbox.getText());
			if(op.equals("+"))
			{
				float res=num1+num2;
				setResult(res);
			}
			else if(op.equals("-"))
			{
				float res=num1-num2;
				setResult(res);
			}
			else if(op.equals("*"))
			{
				float res=num1*num2;
				setResult(res);
			}
			else if(op.equals("/"))
			{
				float res=num1/num2;
				setResult(res);
			}
		}//end of cal() method
		private void setResult(float res)
		{
			int x=(int)res;
			if((float)x==res)
				textbox.setText(String.valueOf(x));
			else
				textbox.setText(String.valueOf(res));
		}
	}
	public static void main(String[] args) 
	{
		new Calculator4();
	}
}
