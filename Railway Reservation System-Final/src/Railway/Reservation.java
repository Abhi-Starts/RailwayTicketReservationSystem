/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Railway;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Rahul Vijayan
 */
public class Reservation extends javax.swing.JFrame {

    /**
     * Creates new form Reservation
     */
    public Reservation() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Connect();
        //passengeridgenerator();
        //LoadUser();
        //LoadUserId();
        //loadUser();
        //databaseChecker();
        passengerID();
        LoadStartPlace();
        LoadDestinationPlace();
        seatnogenerator();
    }
    
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/railway_reservation_system","root","Abhi");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/railwayreservationsystem?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Train_Details.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Train_Details.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
        public void LoadUser()
    {
        try {
            pst = con.prepareStatement("SELECT userId From user where username");
            rs = pst.executeQuery();
            //uid.removeAllItems();
            
            while(rs.next())
            {
               //String uid = rs.getString("uid");
               //String username = rs.getString("username");
            
            // Assuming uid is a JComboBox, you can add the UID and display the username
             //uid.addItem(rs.getString(1));
            }
            
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
        
    public void loadUser()
    {
        
        String ID = pid.getText();
        
        try {
            pst = con.prepareStatement("SELECT userId From user");

            rs = pst.executeQuery();
            
            
            if(rs.next() == true)
            {
                pid.setText(rs.getString(1));
                //trainid1.setText(rs.getString(1));
            }
            
            else
            {
                JOptionPane.showMessageDialog(this, "Record not Found!!!!!!!");
            }
            
            
        } catch (Exception e) {
          e.printStackTrace();
        }
        
    }
    
    
    public void databaseChecker(String enteredData)
    {
        boolean isMatch = false;
        String ID = pid.getText();
        
        try {
            pst = con.prepareStatement("SELECT * FROM user WHERE userId = ?");

            rs = pst.executeQuery();
            
            
            if(rs.next() == true)
            {
                isMatch=true;
                pid.setText(rs.getString(1));
                //trainid1.setText(rs.getString(1));
            }
            
            else
            {
                JOptionPane.showMessageDialog(this, "Record not Found!!!!!!!");
            }
            
            
        } catch (Exception e) {
          e.printStackTrace();
        }
        
    }
    public void passengeridgenerator()
    {
        Random r=new Random();
        int n=r.nextInt(1000000)+1;
        String val=String.valueOf(n);
        pid.setText(val);
    }
    
    public void passengerID()
    {
        try{
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select MAX(userId) from reservation");
            rs.next();
            rs.getString("MAX(userId)");
            
            if(rs.getString("MAX(userId)")== null)
            {
                pid.setText("U-1");
            }
            else{
                long id=Long.parseLong(rs.getString("MAX(userId)").substring(2,rs.getString("MAX(userId)").length()));
                id++;
                pid.setText("U-"+String.format("%d", id));
            }
        }catch(SQLException ex){
            Logger.getLogger(Train_Details.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

       
    
    public void LoadStartPlace()
    {
        try {
            pst = con.prepareStatement("SELECT startPlace From train");
            rs = pst.executeQuery();
            start.removeAllItems();
            
            while(rs.next())
            {
                start.addItem(rs.getString(1));
            }
            
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    
    public void LoadDestinationPlace()
    {
        try {
            pst = con.prepareStatement("SELECT destinationPlace From train");
            rs = pst.executeQuery();
            destination.removeAllItems();
            
            while(rs.next())
            {
                destination.addItem(rs.getString(1));
            }
            
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    
    public void load()
    {
        String StartPlace = start.getSelectedItem().toString();
        String DestinationPlace = destination.getSelectedItem().toString();
        String ID = pid.getText();
        
        try {
            pst = con.prepareStatement("SELECT * FROM train where startPlace = ? and destinationPlace = ?");
            //pst = con.prepareStatement("SELECT uid From user");
            pst.setString(1, StartPlace);
            pst.setString(2, DestinationPlace);
            //pst.setString(1, ID);
            
            rs = pst.executeQuery();
            
            
            if(rs.next() == true)
            {
                trainid.setText(rs.getString(1));
                trainname.setText(rs.getString(2));
                seattype.setText(rs.getString(5));
                price.setText(rs.getString(6));
            }
            
            else
            {
                JOptionPane.showMessageDialog(this, "Record not Found!!!!!!!");
            }
            
            
        } catch (Exception e) {
          e.printStackTrace();
        }
        
    }
    
    public void seatnogenerator()
    {
        Random r=new Random();
        int n=r.nextInt(10)+1;
        String val=String.valueOf(n);
        seatno.setText(val);
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StartPlace = new javax.swing.JLabel();
        DestinationPlace = new javax.swing.JLabel();
        TrainID = new javax.swing.JLabel();
        TrainName = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        SeatType = new javax.swing.JLabel();
        SeatNo = new javax.swing.JLabel();
        start = new javax.swing.JComboBox<>();
        destination = new javax.swing.JComboBox<>();
        Reserve = new javax.swing.JButton();
        SearchButton = new javax.swing.JButton();
        txt_date = new com.toedter.calendar.JCalendar();
        ReservationPanel = new javax.swing.JPanel();
        ReservationLabel = new javax.swing.JLabel();
        GoBackButton = new javax.swing.JButton();
        price = new javax.swing.JLabel();
        pid = new javax.swing.JLabel();
        trainname = new javax.swing.JLabel();
        seattype = new javax.swing.JLabel();
        seatno = new javax.swing.JLabel();
        UserID = new javax.swing.JLabel();
        trainid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        StartPlace.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        StartPlace.setForeground(new java.awt.Color(255, 0, 0));
        StartPlace.setText("Start Place:");
        getContentPane().add(StartPlace);
        StartPlace.setBounds(330, 220, 140, 20);

        DestinationPlace.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        DestinationPlace.setForeground(new java.awt.Color(255, 0, 0));
        DestinationPlace.setText("Destination Place:");
        getContentPane().add(DestinationPlace);
        DestinationPlace.setBounds(330, 260, 190, 30);

        TrainID.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        TrainID.setForeground(new java.awt.Color(255, 0, 0));
        TrainID.setText("Train ID:");
        getContentPane().add(TrainID);
        TrainID.setBounds(330, 320, 110, 20);

        TrainName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        TrainName.setForeground(new java.awt.Color(255, 0, 0));
        TrainName.setText("Train Name:");
        getContentPane().add(TrainName);
        TrainName.setBounds(330, 370, 150, 20);

        Price.setBackground(new java.awt.Color(51, 51, 51));
        Price.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Price.setForeground(new java.awt.Color(255, 0, 0));
        Price.setText("Price:");
        getContentPane().add(Price);
        Price.setBounds(330, 770, 120, 40);

        Date.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Date.setForeground(new java.awt.Color(255, 0, 0));
        Date.setText("Date:");
        getContentPane().add(Date);
        Date.setBounds(330, 410, 70, 30);

        SeatType.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        SeatType.setForeground(new java.awt.Color(255, 0, 0));
        SeatType.setText("Seat Type:");
        getContentPane().add(SeatType);
        SeatType.setBounds(330, 660, 200, 30);

        SeatNo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        SeatNo.setForeground(new java.awt.Color(255, 0, 0));
        SeatNo.setText("Seat No:");
        getContentPane().add(SeatNo);
        SeatNo.setBounds(330, 710, 90, 40);

        start.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(start);
        start.setBounds(580, 210, 230, 27);

        destination.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(destination);
        destination.setBounds(580, 260, 230, 30);

        Reserve.setBackground(new java.awt.Color(0, 51, 255));
        Reserve.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Reserve.setForeground(new java.awt.Color(255, 255, 255));
        Reserve.setText("Reserve");
        Reserve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReserveMouseClicked(evt);
            }
        });
        Reserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReserveActionPerformed(evt);
            }
        });
        getContentPane().add(Reserve);
        Reserve.setBounds(580, 860, 120, 40);

        SearchButton.setBackground(new java.awt.Color(51, 51, 0));
        SearchButton.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        SearchButton.setForeground(new java.awt.Color(255, 255, 255));
        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        getContentPane().add(SearchButton);
        SearchButton.setBounds(910, 240, 120, 40);

        txt_date.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txt_dateAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(txt_date);
        txt_date.setBounds(580, 410, 330, 240);

        ReservationPanel.setBackground(new java.awt.Color(255, 204, 255));
        ReservationPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ReservationPanel.setForeground(new java.awt.Color(255, 204, 255));

        ReservationLabel.setBackground(new java.awt.Color(255, 204, 255));
        ReservationLabel.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        ReservationLabel.setForeground(new java.awt.Color(0, 102, 255));
        ReservationLabel.setText("Reservation");
        ReservationPanel.add(ReservationLabel);

        getContentPane().add(ReservationPanel);
        ReservationPanel.setBounds(450, 10, 460, 80);

        GoBackButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        GoBackButton.setText("Back");
        GoBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GoBackButtonMouseClicked(evt);
            }
        });
        GoBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoBackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(GoBackButton);
        GoBackButton.setBounds(740, 860, 80, 40);

        price.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        price.setForeground(new java.awt.Color(51, 51, 51));
        price.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        getContentPane().add(price);
        price.setBounds(580, 780, 230, 30);

        pid.setBackground(new java.awt.Color(255, 51, 51));
        pid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        pid.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(pid);
        pid.setBounds(580, 160, 230, 30);

        trainname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        trainname.setForeground(new java.awt.Color(51, 51, 51));
        trainname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        getContentPane().add(trainname);
        trainname.setBounds(580, 360, 230, 30);

        seattype.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        seattype.setForeground(new java.awt.Color(51, 51, 51));
        seattype.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        getContentPane().add(seattype);
        seattype.setBounds(580, 660, 230, 30);

        seatno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        seatno.setForeground(new java.awt.Color(51, 51, 51));
        seatno.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        getContentPane().add(seatno);
        seatno.setBounds(580, 720, 230, 30);

        UserID.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        UserID.setForeground(new java.awt.Color(255, 0, 0));
        UserID.setText("User ID:");
        getContentPane().add(UserID);
        UserID.setBounds(330, 160, 160, 40);

        trainid.setBackground(new java.awt.Color(255, 51, 51));
        trainid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        trainid.setForeground(new java.awt.Color(51, 51, 51));
        trainid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        getContentPane().add(trainid);
        trainid.setBounds(580, 310, 230, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReserveActionPerformed
        // TODO add your handling code here:
        
       
        String ID = pid.getText();
        String StartPlace = start.getSelectedItem().toString();
        String DestinationPlace = destination.getSelectedItem().toString();
        String TrainID = trainid.getText();
        String TrainName = trainname.getText();
        String Price = price.getText();
        //String Date=txt_date.getDate();
        SimpleDateFormat date_form = new SimpleDateFormat("dd-MM-yyyy");
        String Date = date_form.format (txt_date.getDate());
        //String SeatType = seattype.getSelectedItem().toString();
        String SeatType = seattype.getText();
        String SeatNo = seatno.getText();
        
        
        try {
            pst = con.prepareStatement("insert into reservation(userId,startPlace,destinationPlace,trainId,trainName,Date,seatType,seatNo,Price)values(?,?,?,?,?,?,?,?,?)");
              
            
            pst.setString(1, ID);
            pst.setString(2, StartPlace);
            pst.setString(3, DestinationPlace);
            pst.setString(4, TrainID);
            pst.setString(5, TrainName);
            pst.setString(6, Date);
            pst.setString(7, SeatType);
            pst.setString(8, SeatNo);
            pst.setString(9, Price);
            
            
            int k = pst.executeUpdate();
            
            if(k == 1)
            {
                JOptionPane.showMessageDialog(this, "Ticket Reserved Successfully");
            }
            
            else
            {
                JOptionPane.showMessageDialog(this, "Ticket Reservation Failed");
            }
                    
                    } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_ReserveActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:
        load();
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void GoBackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GoBackButtonMouseClicked
        // TODO add your handling code here:
        User_Signin u = new User_Signin();
        u.setLocationRelativeTo(null);
        u.setVisible(true);
    }//GEN-LAST:event_GoBackButtonMouseClicked

    private void GoBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoBackButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GoBackButtonActionPerformed

    private void ReserveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReserveMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ReserveMouseClicked

    private void txt_dateAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txt_dateAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dateAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         String enteredData = "example_data";
        /*if (databaseChecker(enteredData)) {
            System.out.println("Entered data matches data in the database.");
        } else {
            System.out.println("Entered data does not match data in the database.");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservation().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JLabel DestinationPlace;
    private javax.swing.JButton GoBackButton;
    private javax.swing.JLabel Price;
    private javax.swing.JLabel ReservationLabel;
    private javax.swing.JPanel ReservationPanel;
    private javax.swing.JButton Reserve;
    private javax.swing.JButton SearchButton;
    private javax.swing.JLabel SeatNo;
    private javax.swing.JLabel SeatType;
    private javax.swing.JLabel StartPlace;
    private javax.swing.JLabel TrainID;
    private javax.swing.JLabel TrainName;
    private javax.swing.JLabel UserID;
    private javax.swing.JComboBox<String> destination;
    private javax.swing.JLabel pid;
    private javax.swing.JLabel price;
    private javax.swing.JLabel seatno;
    private javax.swing.JLabel seattype;
    private javax.swing.JComboBox<String> start;
    private javax.swing.JLabel trainid;
    private javax.swing.JLabel trainname;
    private com.toedter.calendar.JCalendar txt_date;
    // End of variables declaration//GEN-END:variables

    private void addItem(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
