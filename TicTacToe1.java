/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package TicTacToe1;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *
 * @author Debadri Basak
 */
class Announcer extends Dialog implements ActionListener{
    Label l;
    Button b;
    TicTacToe1 t1;
    int cnt;
   // String s1,s2;
    public Announcer(Frame f,String s1,int cnt,TicTacToe1 t)
    {
        super(f,s1,Dialog.ModalityType.DOCUMENT_MODAL);
		this.setLocation(300,100);
        this.cnt=cnt;
        t1=t;
        l = new Label();
        b = new Button("OK");
        if(cnt==1)
        {
            l.setText("Player 1 wins!!!!!");
            l.setBackground(Color.green);
        }
        else if(cnt==2)
        {
            l.setText("Player 2 wins!!!!!");
            l.setBackground(Color.cyan);
        }
        else if(cnt==3)
        {
            l.setText("The game is a tie.");
            l.setBackground(Color.pink);
        }
        else
        {
           l.setText("invalid move");
            l.setBackground(Color.white); 
        }
        b.addActionListener(this);
    }
    public void addThem(){
        setSize(300,300);
        add(l,BorderLayout.CENTER);
        add(b,BorderLayout.SOUTH);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(cnt!=4)
        t1.reset();
        setVisible(false);
    }
}
public class TicTacToe1 extends Frame implements ActionListener {

    int counter;
    int[][] set;
    Announcer a;
    //String s1,s2;
    class Board extends Label implements MouseListener{
        boolean is_avail;
		//this.setLocation(10,10);
        Frame fl;
        int x,y;
        TicTacToe1 t;
        public Board(Frame f,int a1,int b,TicTacToe1 t1){
        is_avail=true;
		this.setLocation(10,0);
        fl=f;
        x=a1;y=b;
        t=t1;
        Font fn=new Font("MonotypeCorsiva",Font.BOLD,92);
        setFont(fn);
        setForeground(Color.white);
        setBackground(Color.white);
        addMouseListener(this);
        }
        public void mouseClicked(MouseEvent e){
            if(!is_avail)
            {
               a=new Announcer(fl,"Problem occured",4,t);
               a.addThem();
            }
            else
            {
                counter++;
                is_avail=false;
                set[x][y]=(counter%2)+1;
                if(counter%2==1)
                {
                    setText("   X");
                    setBackground(Color.red);
                }
                else
                {
                    setText("   O");
                    setBackground(Color.blue);
                }
                if(check()&&(counter%2==1))
                {
                    a=new Announcer(fl,"Game over",1,t);
                    a.addThem();
                }
                else if(check()&&(counter%2==0))
                {
                    a=new Announcer(fl,"Game over",2,t);
                    a.addThem();
                }
                else if(tie())
                {
                    a=new Announcer(fl,"Game over",3,t);
                    a.addThem();
                }
            }
        }
        public void mousePressed(MouseEvent e){
            
        }
        public void mouseReleased(MouseEvent e){
            
        }
        public void mouseEntered(MouseEvent e){
			if(is_avail)
			{
            setBackground(Color.yellow);
			System.out.println(" X = "+x+" Y = "+y);
			}
        }
        public void mouseExited(MouseEvent e){
			if(is_avail)
            setBackground(Color.white);
        }
        public void setIsAvail()
        {
            is_avail=true;
            setBackground(Color.white);
            setText(" ");
        }
    }
    Board[][] B;
    Button b1;
    Panel p;
    public TicTacToe1()//String s11,String s12)
    {
        counter=0;
       // s1=s11;s2=s12;
        set=new int[3][3];
        B=new Board[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                set[i][j]=0;
                B[i][j]=new Board(this,i,j,this);
            }
        }
        b1=new Button("RESET");
        p=new Panel();
    }
    
    public void initialize()
    {
        p.setLayout(new GridLayout(3,3,5,5));
        p.setBackground(Color.black);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                p.add(B[i][j]);
            }
        }
        add(p,BorderLayout.CENTER);
        add(b1,BorderLayout.SOUTH);
        setSize(700,700);
        b1.addActionListener(this);
        setVisible(true);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        }
        );
    }
    public boolean check()
    {
        boolean f1=false,f2=false,f3=false;
        
        for(int i=0;i<3;i++)
        {
            if(set[i][0]==set[i][1]&&set[i][1]==set[i][2]&&set[i][2]!=0)
            {
                f1=true;
                break;
            }
        }
         for(int i=0;i<3;i++)
        {
            if(set[0][i]==set[1][i]&&set[1][i]==set[2][i]&&set[2][i]!=0)
            {
                f2=true;
                break;
            }
        }
         if(set[0][0]==set[1][1]&&set[1][1]==set[2][2]&&set[2][2]!=0)
         {
             f3=true;
         }
         if(set[2][0]==set[1][1]&&set[1][1]==set[0][2]&&set[0][2]!=0)
         {
             f3=true;
         }
         return (f1||f2||f3);
    }
    public boolean tie(){
        boolean f=false;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                f=f||B[i][j].is_avail;
            }
        }
        return !check()&&!f;
    }
    public void actionPerformed(ActionEvent e)
    {
        reset();
    }
    public void reset()
    {
        counter=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                set[i][j]=0;
                B[i][j].setIsAvail();
            }
        }
    }
    public static void main(String[] args) {
        TicTacToe1 obj=new TicTacToe1();//s1,s2);
        obj.initialize();
    } 
}