/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.seagile.bigmart.panel;

import com.seagile.bigmart.connection.MySQL;
import com.seagile.bigmart.gui.Admin;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author DTC
 */
public class EmployeeManage extends javax.swing.JPanel {

    private final Admin adminDashboard;

    /**
     * Creates new form UserManage
     *
     * @param dashboard
     */
    public EmployeeManage(Admin dashboard) {
        initComponents();
        loadGender();
        loadCity();
        loadProvince();
        loadJob();
        loadType();
        loadEmployeeList();
        employeeStatics();
        toggleButtonEffect();
        this.adminDashboard = dashboard;
        // Add Jframe to show notification
        Notifications.getInstance().setJFrame(adminDashboard);

    }

    private void toggleButtonEffect() {
        Color active = new Color(4, 200, 228);
        Color deactive = new Color(210, 91, 91);

        if (jToggleButton1.isSelected()) {
            jToggleButton1.setText("Activate Account");
            jToggleButton1.setBackground(active);
        } else {
            jToggleButton1.setText("Deactivate Account");
            jToggleButton1.setBackground(deactive);
        }
    }

    private void createAccED() {
        if (jCheckBox1.isSelected()) {
            jTextField19.setEnabled(true);
            jTextField20.setEnabled(true);
            jComboBox7.setEnabled(true);
        } else {
            jTextField19.setEnabled(false);
            jTextField19.setText("");
            jTextField20.setEnabled(false);
            jTextField20.setText("");
            jComboBox7.setEnabled(false);
            jComboBox7.setSelectedIndex(0);
        }
    }

    private void employeeStatics() {
        try {
            ResultSet result = MySQL.search("SELECT * FROM `employee`");

            int totalEmployee = 0;
            int activeEmployee = 0;
            int inactiveEmployee = 0;
            int adminCount = 0;

            while (result.next()) {
                totalEmployee++;
                if ("1".equals(result.getString("status_id"))) {
                    activeEmployee++;
                } else if ("2".equals(result.getString("status_id"))) {
                    inactiveEmployee++;
                }

                if ("1".equals(result.getString("acc_type_id"))) {
                    adminCount++;
                }
            }

            jLabel2.setText(String.valueOf(totalEmployee + " "));
            jLabel10.setText(String.valueOf(activeEmployee + " "));
            jLabel12.setText(String.valueOf(inactiveEmployee + " "));
            jLabel14.setText(String.valueOf(adminCount + " "));

            loadEmployeeList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeList() {
        try {
            ResultSet employeeResult = MySQL.search("SELECT * FROM `employee` "
                    + "INNER JOIN `gender` ON `employee`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `city` ON `employee`.`city_id` = `city`.`id` "
                    + "INNER JOIN `job` ON `employee`.`job_id` = `job`.`id` "
                    + "INNER JOIN `status` ON `employee`.`status_id` = `status`.`id`"
                    + "ORDER BY `employee`.`id` ASC");

            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);

            while (employeeResult.next()) {
                Vector<String> employeeList = new Vector();

                employeeList.add(employeeResult.getString("id"));
                employeeList.add(employeeResult.getString("fname"));
                employeeList.add(employeeResult.getString("lname"));
                employeeList.add(employeeResult.getString("nic"));
                employeeList.add(employeeResult.getString("mobile"));
                employeeList.add(employeeResult.getString("email"));
                employeeList.add(employeeResult.getString("gender_name"));
                employeeList.add(employeeResult.getString("city_name"));
                employeeList.add(employeeResult.getString("job_title"));
                employeeList.add(employeeResult.getString("status_name"));

                tableModel.addRow(employeeList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeFields() {
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jComboBox1.setSelectedIndex(0);

        jTextField13.setText("");
        jTextField12.setText("");
        jComboBox6.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField18.setText("");

        jComboBox5.setSelectedIndex(0);

        jCheckBox1.setSelected(false);

        jTextField19.setText("");
        jTextField20.setText("");
        jComboBox7.setSelectedIndex(0);

    }

    private void loadGender() {
        try {
            ResultSet genderResult = MySQL.search("SELECT * FROM `gender`");
            Vector<String> gender = new Vector();
            gender.add("Select Gender");
            while (genderResult.next()) {
                gender.add(genderResult.getString("gender_name"));
            }
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(gender);
            this.jComboBox1.setModel(comboBoxModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCity() {
        try {
            ResultSet cityResult = MySQL.search("SELECT * FROM `city`");
            Vector<String> city = new Vector();
            city.add("Select City");
            while (cityResult.next()) {
                city.add(cityResult.getString("city_name"));
            }
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(city);
            this.jComboBox6.setModel(comboBoxModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProvince() {
        try {
            ResultSet provinceResult = MySQL.search("SELECT * FROM `province`");
            Vector<String> province = new Vector();
            province.add("Select Province");
            while (provinceResult.next()) {
                province.add(provinceResult.getString("province_name"));
            }
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(province);
            this.jComboBox2.setModel(comboBoxModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadJob() {
        try {
            ResultSet jobResult = MySQL.search("SELECT * FROM `job`");
            Vector<String> job = new Vector();
            job.add("Select Job");
            while (jobResult.next()) {
                job.add(jobResult.getString("job_title") + " - " + jobResult.getString("description"));
            }
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(job);
            this.jComboBox5.setModel(comboBoxModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadType() {
        try {
            ResultSet typeResult = MySQL.search("SELECT * FROM `acc_type`");
            Vector<String> type = new Vector();
            type.add("Select Type");
            while (typeResult.next()) {
                type.add(typeResult.getString("acc_name"));
            }
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(type);
            this.jComboBox7.setModel(comboBoxModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        roundedBackground2 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roundedBackground1 = new com.seagile.bigmart.component.RoundedBackground();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        roundedBackground3 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        roundedBackground4 = new com.seagile.bigmart.component.RoundedBackground();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        roundedBackground5 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        roundedBackground7 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField18 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        roundedBackground8 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel7 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        roundedBackground6 = new com.seagile.bigmart.component.RoundedBackground();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        roundedBackground9 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jComboBox7 = new javax.swing.JComboBox<>();

        jTabbedPane1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        roundedBackground2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel1.setText("Total Employees");

        jLabel2.setBackground(new java.awt.Color(204, 255, 204));
        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("36");
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout roundedBackground2Layout = new javax.swing.GroupLayout(roundedBackground2);
        roundedBackground2.setLayout(roundedBackground2Layout);
        roundedBackground2Layout.setHorizontalGroup(
            roundedBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedBackground2Layout.setVerticalGroup(
            roundedBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundedBackground1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "NIC", "Mobile", "Email", "Gender", "City", "Job", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setBackground(new java.awt.Color(204, 255, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/seagile/bigmart/img/search.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel5.setText("Employee List");

        javax.swing.GroupLayout roundedBackground1Layout = new javax.swing.GroupLayout(roundedBackground1);
        roundedBackground1.setLayout(roundedBackground1Layout);
        roundedBackground1Layout.setHorizontalGroup(
            roundedBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                    .addGroup(roundedBackground1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundedBackground1Layout.setVerticalGroup(
            roundedBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundedBackground3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setText("Active Employees");

        jLabel10.setBackground(new java.awt.Color(204, 255, 204));
        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("29 ");
        jLabel10.setOpaque(true);

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setText("Inactive Employees");

        jLabel12.setBackground(new java.awt.Color(204, 255, 204));
        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("7 ");
        jLabel12.setOpaque(true);

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setText("Admins/Managers");

        jLabel14.setBackground(new java.awt.Color(204, 255, 204));
        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("2 ");
        jLabel14.setOpaque(true);

        javax.swing.GroupLayout roundedBackground3Layout = new javax.swing.GroupLayout(roundedBackground3);
        roundedBackground3.setLayout(roundedBackground3Layout);
        roundedBackground3Layout.setHorizontalGroup(
            roundedBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        roundedBackground3Layout.setVerticalGroup(
            roundedBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedBackground3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        roundedBackground4.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(4, 200, 128));
        jButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton1.setText("Register New");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Get Report");
        jButton2.setBorderPainted(false);

        javax.swing.GroupLayout roundedBackground4Layout = new javax.swing.GroupLayout(roundedBackground4);
        roundedBackground4.setLayout(roundedBackground4Layout);
        roundedBackground4Layout.setHorizontalGroup(
            roundedBackground4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundedBackground4Layout.setVerticalGroup(
            roundedBackground4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(roundedBackground2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(roundedBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(roundedBackground2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedBackground3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedBackground4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Employees List", jPanel1);

        roundedBackground5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel3.setText("General Informations");

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel16.setText("First Name");

        jTextField7.setBackground(new java.awt.Color(204, 255, 204));
        jTextField7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jTextField8.setBackground(new java.awt.Color(204, 255, 204));
        jTextField8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField8.setMaximumSize(new java.awt.Dimension(100, 32));

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel17.setText("Last Name");

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("NIC");

        jTextField9.setBackground(new java.awt.Color(204, 255, 204));
        jTextField9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jTextField10.setBackground(new java.awt.Color(204, 255, 204));
        jTextField10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel19.setText("Mobile");

        jTextField11.setBackground(new java.awt.Color(204, 255, 204));
        jTextField11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setText("Email");

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel21.setText("Gender");

        jComboBox1.setBackground(new java.awt.Color(204, 255, 204));
        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout roundedBackground5Layout = new javax.swing.GroupLayout(roundedBackground5);
        roundedBackground5.setLayout(roundedBackground5Layout);
        roundedBackground5Layout.setHorizontalGroup(
            roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel3)
                    .addGroup(roundedBackground5Layout.createSequentialGroup()
                        .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        roundedBackground5Layout.setVerticalGroup(
            roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedBackground5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedBackground5Layout.createSequentialGroup()
                        .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedBackground5Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedBackground5Layout.createSequentialGroup()
                                .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedBackground5Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundedBackground5Layout.createSequentialGroup()
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextField11))))))
                .addContainerGap())
        );

        roundedBackground7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel4.setText("Address Informations");

        jTextField18.setBackground(new java.awt.Color(204, 255, 204));
        jTextField18.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel28.setText("Postal Code");

        jLabel30.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel30.setText("Province");

        jComboBox2.setBackground(new java.awt.Color(204, 255, 204));
        jComboBox2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox6.setBackground(new java.awt.Color(204, 255, 204));
        jComboBox6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("City");

        jTextField12.setBackground(new java.awt.Color(204, 255, 204));
        jTextField12.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setText("Line Two");

        jTextField13.setBackground(new java.awt.Color(204, 255, 204));
        jTextField13.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel27.setText("Line One");

        javax.swing.GroupLayout roundedBackground7Layout = new javax.swing.GroupLayout(roundedBackground7);
        roundedBackground7.setLayout(roundedBackground7Layout);
        roundedBackground7Layout.setHorizontalGroup(
            roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundedBackground7Layout.createSequentialGroup()
                        .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField12)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        roundedBackground7Layout.setVerticalGroup(
            roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedBackground7Layout.createSequentialGroup()
                        .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedBackground7Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(roundedBackground7Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField12))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jTextField18)))
                    .addGroup(roundedBackground7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        roundedBackground8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel7.setText("Job Informations");

        jLabel34.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel34.setText("Job Title");

        jComboBox5.setBackground(new java.awt.Color(204, 255, 204));
        jComboBox5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout roundedBackground8Layout = new javax.swing.GroupLayout(roundedBackground8);
        roundedBackground8.setLayout(roundedBackground8Layout);
        roundedBackground8Layout.setHorizontalGroup(
            roundedBackground8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedBackground8Layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(roundedBackground8Layout.createSequentialGroup()
                        .addGroup(roundedBackground8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundedBackground8Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(214, 214, 214))
                    .addGroup(roundedBackground8Layout.createSequentialGroup()
                        .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        roundedBackground8Layout.setVerticalGroup(
            roundedBackground8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedBackground8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addGroup(roundedBackground8Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setBackground(new java.awt.Color(4, 200, 128));
        jButton4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton4.setText("Register");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 102, 102));
        jButton6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Update");
        jButton6.setBorderPainted(false);
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jToggleButton1.setBackground(new java.awt.Color(210, 91, 91));
        jToggleButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("Deactivate Account");
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setEnabled(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        roundedBackground6.setBackground(new java.awt.Color(255, 255, 255));

        jButton7.setBackground(new java.awt.Color(102, 102, 102));
        jButton7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Statics");
        jButton7.setBorderPainted(false);
        jButton7.setEnabled(false);

        jButton8.setBackground(new java.awt.Color(102, 102, 102));
        jButton8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Clear");
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedBackground6Layout = new javax.swing.GroupLayout(roundedBackground6);
        roundedBackground6.setLayout(roundedBackground6Layout);
        roundedBackground6Layout.setHorizontalGroup(
            roundedBackground6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedBackground6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap())
        );
        roundedBackground6Layout.setVerticalGroup(
            roundedBackground6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedBackground6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        roundedBackground9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel8.setText("Account Informations");

        jLabel35.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel35.setText("Username");

        jCheckBox1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jCheckBox1.setText("Create Account");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel36.setText("Password");

        jLabel37.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel37.setText("Account Type");

        jTextField19.setBackground(new java.awt.Color(204, 255, 204));
        jTextField19.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField19.setEnabled(false);

        jTextField20.setBackground(new java.awt.Color(204, 255, 204));
        jTextField20.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField20.setEnabled(false);

        jComboBox7.setBackground(new java.awt.Color(204, 255, 204));
        jComboBox7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.setEnabled(false);

        javax.swing.GroupLayout roundedBackground9Layout = new javax.swing.GroupLayout(roundedBackground9);
        roundedBackground9.setLayout(roundedBackground9Layout);
        roundedBackground9Layout.setHorizontalGroup(
            roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(roundedBackground9Layout.createSequentialGroup()
                        .addGroup(roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField20)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel8)
                    .addComponent(jCheckBox1))
                .addContainerGap())
        );
        roundedBackground9Layout.setVerticalGroup(
            roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(jLabel37)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedBackground9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jTextField19)
                    .addComponent(jTextField20))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedBackground5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundedBackground7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundedBackground8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(roundedBackground6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundedBackground9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedBackground6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Employee Manage", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        String nic = jTextField9.getText();

        if (jToggleButton1.isSelected()) {
            try {
                MySQL.iud("UPDATE `employee` SET `status_id` = '2' WHERE `employee`.`nic` = '" + nic + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            toggleButtonEffect();
            employeeStatics();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Employee Account Diactivated");

        } else {
            try {
                MySQL.iud("UPDATE `employee` SET `status_id` = '1' WHERE `employee`.`nic` = '" + nic + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            toggleButtonEffect();
            employeeStatics();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Employee Account Activated");

        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        createAccED();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String fname = jTextField7.getText();
        String lname = jTextField8.getText();
        String nic = jTextField9.getText();
        String mobile = jTextField10.getText();
        String email = jTextField11.getText();
        String gender = (String) jComboBox1.getSelectedItem();

        String lineOne = jTextField13.getText();
        String lineTwo = jTextField12.getText();
        String city = (String) jComboBox6.getSelectedItem();
        String province = (String) jComboBox2.getSelectedItem();
        String pcode = jTextField18.getText();

        String job = (String) jComboBox5.getSelectedItem();

        String username = jTextField19.getText();
        String password = jTextField20.getText();
        String accType = (String) jComboBox7.getSelectedItem();

        boolean isValid = true;

        if (fname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "First name is empty");
        } else if (lname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Last name is empty");
        } else if (nic.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "NIC number is empty");
        } else if (mobile.isEmpty()) { //Validate this
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Mobile is empty");
        } else if (!mobile.matches("^(0{1})(7{1})([0|1|2|4|5|6|7|8]{1})([0-9]{7})")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Mobile number is invalid");
        } else if (email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Email address is empty");
        } else if (!email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Email address is invalid");
        } else if (gender.equals("Select Gender")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Gender is not selected");
        } else if (lineOne.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Line one is empty");
        } else if (lineTwo.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Line two is empty");
        } else if (city.equals("Select City")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "City is not selected");
        } else if (province.equals("Select Province")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Province is not selected");
        } else if (pcode.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Postal code is empty");
        } else if (job.equals("Select Job")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Job is not selected");
        } else if (jCheckBox1.isSelected()) {
            if (username.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Username is empty");
                isValid = false;
            } else if (password.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Password is empty");
                isValid = false;
            } else if (accType.equals("Select Type")) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Account type is not selected");
                isValid = false;
            }
        }

        if (isValid) {
            //gender
            String gender_id = "";
            try {
                ResultSet genderResult = MySQL.search("SELECT `id` FROM `gender` WHERE `gender_name` = '" + gender + "'");
                if (genderResult.next()) {
                    gender_id = genderResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //city
            String city_id = "";
            try {
                ResultSet cityResult = MySQL.search("SELECT `id` FROM `city` WHERE `city_name` = '" + city + "'");
                if (cityResult.next()) {
                    city_id = cityResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //province
            String province_id = "";
            try {
                ResultSet provinceResult = MySQL.search("SELECT `id` FROM `province` WHERE `province_name` = '" + province + "'");
                if (provinceResult.next()) {
                    province_id = provinceResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //job
            String job_id = "";

            String[] split = job.split(" - ");
            String jobTitle = split[0];

            try {
                ResultSet jobResult = MySQL.search("SELECT `id` FROM `job` WHERE `job_title` = '" + jobTitle + "'");
                if (jobResult.next()) {
                    job_id = jobResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //account_type
            String accType_id = "";
            try {
                ResultSet typeResult = MySQL.search("SELECT `id` FROM `acc_type` WHERE `acc_name` = '" + accType + "'");
                if (typeResult.next()) {
                    accType_id = typeResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                ResultSet result = MySQL.search("SELECT * FROM `employee` WHERE `nic` = '" + nic + "' OR `username` = '" + username + "'");
                if (result.next()) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "This Employee is already registered");
                } else {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedNewDate = simpleDateFormat.format(new Date());

                    String query = "";

                    if (jCheckBox1.isSelected()) {
                        query = ("INSERT INTO `employee` (`fname`,`lname`,`nic`,`mobile`,`email`,`gender_id`,`line_one`,`line_two`,`city_id`,`province_id`,`pcode`,`job_id`,`username`,`password`,`acc_type_id`,`joined_date`) VALUES"
                                + "('" + fname + "','" + lname + "','" + nic + "','" + mobile + "','" + email + "','" + gender_id + "','" + lineOne + "','" + lineTwo + "','" + city_id + "','" + province_id + "','" + pcode + "','" + job_id + "','" + username + "','" + password + "','" + accType_id + "','" + formattedNewDate + "')");
                    } else {
                        query = ("INSERT INTO `employee` (`fname`,`lname`,`nic`,`mobile`,`email`,`gender_id`,`line_one`,`line_two`,`city_id`,`province_id`,`pcode`,`job_id`,`joined_date`) VALUES"
                                + "('" + fname + "','" + lname + "','" + nic + "','" + mobile + "','" + email + "','" + gender_id + "','" + lineOne + "','" + lineTwo + "','" + city_id + "','" + province_id + "','" + pcode + "','" + job_id + "','" + formattedNewDate + "')");
                    }

                    try {
                        MySQL.iud(query);
                        removeFields();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Employee Register Successful");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            int selectedRow = jTable1.getSelectedRow();
            String fname = (String) jTable1.getValueAt(selectedRow, 1);
            String lname = (String) jTable1.getValueAt(selectedRow, 2);
            String nic = (String) jTable1.getValueAt(selectedRow, 3);
            String mobile = (String) jTable1.getValueAt(selectedRow, 4);
            String email = (String) jTable1.getValueAt(selectedRow, 5);
            String gender = (String) jTable1.getValueAt(selectedRow, 6);
            String city = (String) jTable1.getValueAt(selectedRow, 7);
            String status = (String) jTable1.getValueAt(selectedRow, 9);

            try {
                ResultSet result = MySQL.search("SELECT * FROM `employee` "
                        + "INNER JOIN `province` ON `employee`.`province_id` = `province`.`id` "
                        + "WHERE `nic`= '" + nic + "'");

                if (result.next()) {
                    jTabbedPane1.setSelectedIndex(1);

                    jTextField7.setText(fname);
                    jTextField8.setText(lname);
                    jTextField9.setText(nic);
                    jTextField10.setText(mobile);
                    jTextField11.setText(email);
                    jComboBox1.setSelectedItem(gender);

                    jTextField13.setText(result.getString("line_one"));
                    jTextField12.setText(result.getString("line_two"));
                    jComboBox6.setSelectedItem(city);
                    jComboBox2.setSelectedIndex(result.getInt("province_id"));
                    jTextField18.setText(result.getString("pcode"));

                    jComboBox5.setSelectedIndex(result.getInt("job_id"));

                    if (result.getString("username") == null) {
                        jCheckBox1.setSelected(false);
                        createAccED();
                    } else {
                        jCheckBox1.setSelected(true);
                        createAccED();
                        jTextField19.setText(result.getString("username"));
                        jTextField20.setText(result.getString("password"));
                        jComboBox7.setSelectedIndex(result.getInt("acc_type_id"));
                    }

                    jToggleButton1.setEnabled(true);
                    jButton6.setEnabled(true);
                    jButton4.setEnabled(false);

                    if ("ACTIVE".equals(status)) {
                        jToggleButton1.setSelected(false);
                    } else if ("DEACTIVE".equals(status)) {
                        jToggleButton1.setSelected(true);
                    }

                    toggleButtonEffect();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        removeFields();
        jToggleButton1.setSelected(false);
        jToggleButton1.setEnabled(false);
        jButton6.setEnabled(false);
        jButton4.setEnabled(true);
        toggleButtonEffect();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String fname = jTextField7.getText();
        String lname = jTextField8.getText();
        String nic = jTextField9.getText();
        String mobile = jTextField10.getText();
        String email = jTextField11.getText();
        String gender = (String) jComboBox1.getSelectedItem();

        String lineOne = jTextField13.getText();
        String lineTwo = jTextField12.getText();
        String city = (String) jComboBox6.getSelectedItem();
        String province = (String) jComboBox2.getSelectedItem();
        String pcode = jTextField18.getText();

        String job = (String) jComboBox5.getSelectedItem();

        String username = jTextField19.getText();
        String password = jTextField20.getText();
        String accType = (String) jComboBox7.getSelectedItem();

        boolean isValid = true;

        if (fname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "First name is empty");
        } else if (lname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Last name is empty");
        } else if (nic.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "NIC number is empty");
        } else if (mobile.isEmpty()) { //Validate this
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Mobile is empty");
        } else if (!mobile.matches("^(0{1})(7{1})([0|1|2|4|5|6|7|8]{1})([0-9]{7})")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Mobile number is invalid");
        } else if (email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Email address is empty");
        } else if (!email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Email address is invalid");
        } else if (gender.equals("Select Gender")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Gender is not selected");
        } else if (lineOne.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Line one is empty");
        } else if (lineTwo.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Line two is empty");
        } else if (city.equals("Select City")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "City is not selected");
        } else if (province.equals("Select Province")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Province is not selected");
        } else if (pcode.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Postal code is empty");
        } else if (job.equals("Select Job")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Job is not selected");
        } else if (jCheckBox1.isSelected()) {
            if (username.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Username is empty");
                isValid = false;
            } else if (password.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Password is empty");
                isValid = false;
            } else if (accType.equals("Select Type")) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Account type is not selected");
                isValid = false;
            }
        }

        if (isValid) {
            //gender
            String gender_id = "";
            try {
                ResultSet genderResult = MySQL.search("SELECT `id` FROM `gender` WHERE `gender_name` = '" + gender + "'");
                if (genderResult.next()) {
                    gender_id = genderResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //city
            String city_id = "";
            try {
                ResultSet cityResult = MySQL.search("SELECT `id` FROM `city` WHERE `city_name` = '" + city + "'");
                if (cityResult.next()) {
                    city_id = cityResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //province
            String province_id = "";
            try {
                ResultSet provinceResult = MySQL.search("SELECT `id` FROM `province` WHERE `province_name` = '" + province + "'");
                if (provinceResult.next()) {
                    province_id = provinceResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //job
            String job_id = "";

            String[] split = job.split(" - ");
            String jobTitle = split[0];

            try {
                ResultSet jobResult = MySQL.search("SELECT `id` FROM `job` WHERE `job_title` = '" + jobTitle + "'");
                if (jobResult.next()) {
                    job_id = jobResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //account_type
            String accType_id = "";
            try {
                ResultSet typeResult = MySQL.search("SELECT `id` FROM `acc_type` WHERE `acc_name` = '" + accType + "'");
                if (typeResult.next()) {
                    accType_id = typeResult.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedNewDate = simpleDateFormat.format(new Date());

                String query = "";

                if (jCheckBox1.isSelected()) {
                    query = ("UPDATE `employee` SET "
                            + "`fname` = '" + fname + "', `lname` = '" + lname + "', `mobile` = '" + mobile + "', `email` = '" + email + "', `gender_id` = '" + gender_id + "', "
                            + "`line_one` = '" + lineOne + "', `line_two` = '" + lineTwo + "', `city_id` = '" + city_id + "', `province_id` = '" + province_id + "', `pcode` = '" + pcode + "', "
                            + "`job_id` = '" + job_id + "', `username` = '" + username + "', `password` = '" + password + "', `acc_type_id` = '" + accType_id + "', `joined_date` = '" + formattedNewDate + "' "
                            + "WHERE `nic` = '" + nic + "'");
                } else {
                    query = ("UPDATE `employee` SET "
                            + "`fname` = '" + fname + "', `lname` = '" + lname + "', `mobile` = '" + mobile + "', `email` = '" + email + "', `gender_id` = '" + gender_id + "', "
                            + "`line_one` = '" + lineOne + "', `line_two` = '" + lineTwo + "', `city_id` = '" + city_id + "', `province_id` = '" + province_id + "', `pcode` = '" + pcode + "', "
                            + "`job_id` = '" + job_id + "', `joined_date` = '" + formattedNewDate + "' "
                            + "WHERE `nic` = '" + nic + "'");
                }

                try {
                    MySQL.iud(query);
                    removeFields();
                    employeeStatics();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Employee Update Successful");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String searchTerm = jTextField1.getText();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try {
            ResultSet result = MySQL.search("SELECT * FROM `employee` "
                    + "INNER JOIN `gender` ON `employee`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `city` ON `employee`.`city_id` = `city`.`id` "
                    + "INNER JOIN `job` ON `employee`.`job_id` = `job`.`id` "
                    + "INNER JOIN `status` ON `employee`.`status_id` = `status`.`id`"
                    + "WHERE `employee`.`fname` LIKE '%" + searchTerm + "%' OR "
                    + "`employee`.`lname` LIKE '%" + searchTerm + "%' OR"
                    + "`employee`.`nic` LIKE '%" + searchTerm + "%' OR"
                    + "`employee`.`mobile` LIKE '%" + searchTerm + "%' OR"
                    + "`employee`.`email` LIKE '%" + searchTerm + "%' OR"
                    + "`employee`.`line_one` LIKE '%" + searchTerm + "%' OR"
                    + "`employee`.`line_two` LIKE '%" + searchTerm + "%' OR"
                    + "`city`.`city_name` LIKE '%" + searchTerm + "%'"
                    + " ORDER BY `employee`.`id` ASC");

            while (result.next()) {
                Vector<String> row = new Vector<>();
                row.add(result.getString("id"));
                row.add(result.getString("fname"));
                row.add(result.getString("lname"));
                row.add(result.getString("nic"));
                row.add(result.getString("mobile"));
                row.add(result.getString("email"));
                row.add(result.getString("gender_name"));
                row.add(result.getString("city_name"));
                row.add(result.getString("job_title"));
                row.add(result.getString("status_name"));

                model.addRow(row);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton1;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground1;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground2;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground3;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground4;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground5;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground6;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground7;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground8;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground9;
    // End of variables declaration//GEN-END:variables
}
