package guis;

import db_objs.MyJDBC;
import db_objs.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Color.color;

/*
    This gui will allow user to login or launch the register gui
    This extends from the BaseFrame which emans we will need to define our own addGuiComponent()
 */
public class LoginGui extends BaseFrame{
    Image bankImage;
    public LoginGui(){
        super("Banking App Login");
        
       
    }

    @Override
    protected void addGuiComponents() {
        // create banking app label

        
        JLabel lable=new JLabel(loadImage("C:\\Users\\tejkp\\Documents\\bank project\\guis\\bankedit.png"));
        lable.setBounds(10,10,60,60);
        add(lable);

        
        JLabel bankingAppLabel = new JLabel("Banking Application");
        bankingAppLabel.setForeground(color.text);

        // set the location and the size of the gui component
        bankingAppLabel.setBounds(15, 20, super.getWidth(), 40);

        // change the font style
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));

        // center text in Jlabel
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add to gui
        add(bankingAppLabel);

        // username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(color.text);

        // getWidth() returns us the width of our frame which is about 420
        usernameLabel.setBounds(20, 120, getWidth() - 30, 24);

        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(usernameLabel);

        // create username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(20, 160, getWidth() - 50, 40);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 28));
        usernameField.setBackground(color.secondary);
        usernameField.setForeground(color.text);
        add(usernameField);

        // create password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 280, getWidth() - 50, 24);
        passwordLabel.setForeground(color.text);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);

        // create password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 320, getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
        passwordField.setBackground(color.secondary);
        passwordField.setForeground(color.text);
        add(passwordField);

        // create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20, 460, getWidth() - 50, 40);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.setBackground(color.secondary);
       loginButton.setForeground(color.text);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = usernameField.getText();

                // get password
                String password = String.valueOf(passwordField.getPassword());

                // validate login
                User user = MyJDBC.validateLogin(username, password);

                // if user is null it means invalid otherwise it is a valid account
                if(user!=null){
                    // means valid login

                    // dispose this gui
                    LoginGui.this.dispose();

                    // launch bank app gui
                    BankingAppGui bankingAppGui = new BankingAppGui(user);
                    bankingAppGui.setVisible(true);

                    // show success dialog
                    JOptionPane.showMessageDialog(bankingAppGui, "Login Successfully!");
                }else{
                    // invalid login
                    JOptionPane.showMessageDialog(LoginGui.this, "please enter name and password");
                }
                
            }
        });
        add(loginButton);

        // create register label
        JLabel registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0, 510, getWidth() - 10, 30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // adds an event listener so when the mouse is clicked it will launch the register gui
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this gui
                LoginGui.this.dispose();

                // launch the register gui
                new RegisterGui().setVisible(true);
            }
        });

        add(registerLabel);
    }
     private ImageIcon loadImage(String resourcePath)
    {
        try
        {
            BufferedImage image=ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}















