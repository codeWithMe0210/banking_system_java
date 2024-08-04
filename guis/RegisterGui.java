package guis;

import db_objs.MyJDBC;

import javax.imageio.ImageIO;
import javax.swing.*;

import Color.color;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegisterGui extends BaseFrame{
    public RegisterGui(){
        super("Banking App Register");
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
        passwordLabel.setBounds(20, 220, getWidth() - 50, 24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        passwordLabel.setForeground(color.text);
        add(passwordLabel);

        // create password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 260, getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
        passwordField.setForeground(color.text);
        passwordField.setBackground(color.secondary);
        add(passwordField);

        // re-type password label
        JLabel rePasswordLabel = new JLabel("Re-type Password:");
        rePasswordLabel.setBounds(20, 320, getWidth() - 50, 40);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        rePasswordLabel.setForeground(color.text);
    
        
        add(rePasswordLabel);

        // create re-type password field
        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(20, 360, getWidth() - 50, 40);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 28));
        rePasswordField.setForeground(color.text);
        rePasswordField.setBackground(color.secondary);
        
        add(rePasswordField);

        // create register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(20, 460, getWidth() - 50, 40);
        registerButton.setFont(new Font("Dialog", Font.BOLD, 20));
        registerButton.setBackground(color.secondary);
        registerButton.setForeground(color.text);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = usernameField.getText();

                // get password
                String password = String.valueOf(passwordField.getPassword());

                // get re password
                String rePassword = String.valueOf(rePasswordField.getPassword());

                // we will need to validate the user input
                if(validateUserInput(username, password, rePassword)){
                    // attempt to register the user to the database
                    if(MyJDBC.register(username, password)){
                        // register success
                        // dispose of this gui
                        RegisterGui.this.dispose();

                        // launch the login gui
                       LoginGui loginGui = new LoginGui();
                        loginGui.setVisible(true);

                        // create a result dialog
                        JOptionPane.showMessageDialog(RegisterGui.this, "Registered Account Successfully!");
                    }else{
                        // register failed
                        JOptionPane.showMessageDialog(RegisterGui.this, "Error: Username already taken");
                    }
                }else{
                    // invalid user input
                    JOptionPane.showMessageDialog(RegisterGui.this,
                            "Error: Username must be at least 6 characters\n" +
                            "and/or Password must match");
                }
            }
        });
        add(registerButton);

        // create login label
        JLabel loginLabel = new JLabel("<html><a href=\"#\">Have an account? Sign-in here</a></html>");
        loginLabel.setBounds(0, 510, getWidth() - 10, 30);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this gui
                RegisterGui.this.dispose();

                // launch the login gui
                new LoginGui().setVisible(true);
            }
        });
        add(loginLabel);
    }

    private boolean validateUserInput(String username, String password, String rePassword){
        // all fields must have a value
        if(username.equals(null)||username.length()<6|| password.length() == 0 || rePassword.length() == 0) return false;

        // username has to be at least 6 characters long
       // if(username.length() < 6) return false;

        // password and repassword must be the same
        if(!password.equals(rePassword)) return false;

        // passes validation
        return true;
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
















