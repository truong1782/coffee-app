/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Database.Connect;
import Process.CUSTOMER;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;
import other.VNCharacterUtils;

/**
 *
 * @author ASUS
 */
public class FrmCustomer extends javax.swing.JFrame {

    CUSTOMER cust = new CUSTOMER();
    Connect con = new Connect();
    private Component frame;

    /**
     * Creates new form FrmCustomer
     */
    public FrmCustomer() {
        initComponents();
        this.setExtendedState(FrmMenu.MAXIMIZED_BOTH);
        tableContent();
        try {
            ShowData();
        } catch (SQLException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tableContent() {
        JTableHeader tableheader = tblListCustomer.getTableHeader();
        tableheader.setFont(new Font("Tahoma", Font.BOLD, 16));

        DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer();
        textRenderer.setHorizontalAlignment(JLabel.LEFT);

        for (int i = 0; i < tblListCustomer.getColumnCount(); i++) {
            tblListCustomer.getColumnModel().getColumn(i).setCellRenderer(textRenderer);
        }
    }

    public void ShowData() throws SQLException {
        ResultSet result = cust.getAllCustomer();
        DefaultTableModel tableModel = (DefaultTableModel) tblListCustomer.getModel();
        tableModel.setRowCount(0); //clear table
        try {
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[4];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 
                rows[2] = result.getString(3); // lấy dữ liệu tai cột số 3
                rows[3] = result.getString(4); // lấy dữ liệu tai cột số 4 
                tableModel.addRow(rows); // đưa dòng dữ liệu vào tableModel
            }
        } catch (SQLException e) {
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

        pnlNorth = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListCustomer = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        pnlEast = new javax.swing.JPanel();
        lblTenDoUong1 = new javax.swing.JLabel();
        txtNameCustomer = new javax.swing.JTextField();
        lblGiaTien1 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        lblMoTa1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBack.setText("Quay lại");
        btnBack.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRANG QUẢN LÝ THÔNG TIN KHÁCH HÀNG");
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pnlNorthLayout = new javax.swing.GroupLayout(pnlNorth);
        pnlNorth.setLayout(pnlNorthLayout);
        pnlNorthLayout.setHorizontalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(18, 18, 18)
                .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNorthLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addComponent(jSeparator1)))
        );
        pnlNorthLayout.setVerticalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCenter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jScrollPane2.setAlignmentX(2.0F);
        jScrollPane2.setAlignmentY(2.0F);

        tblListCustomer.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblListCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "SĐT", "Ghi chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListCustomer.setShowGrid(true);
        tblListCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblListCustomer);

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTimKiem.setText("Tìm kiếm");
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                    .addComponent(txtTimKiem))
                .addContainerGap())
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlEast.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        pnlEast.setRequestFocusEnabled(false);
        pnlEast.setVerifyInputWhenFocusTarget(false);

        lblTenDoUong1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTenDoUong1.setText("Tên khách hàng:");

        txtNameCustomer.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lblGiaTien1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblGiaTien1.setText("SĐT:");

        txtPhoneNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lblMoTa1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblMoTa1.setText("Ghi chú:");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtNote.setColumns(20);
        txtNote.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNote.setLineWrap(true);
        txtNote.setRows(5);
        txtNote.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(txtNote);

        btnSua.setBackground(new java.awt.Color(102, 255, 204));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua.setText("Cập nhật");
        btnSua.setBorderPainted(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 0, 0));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(153, 255, 153));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setText("Thêm ");
        btnThem.setBorderPainted(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(255, 255, 102));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEastLayout = new javax.swing.GroupLayout(pnlEast);
        pnlEast.setLayout(pnlEastLayout);
        pnlEastLayout.setHorizontalGroup(
            pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEastLayout.createSequentialGroup()
                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEastLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlEastLayout.createSequentialGroup()
                                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlEastLayout.createSequentialGroup()
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlEastLayout.createSequentialGroup()
                                        .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblGiaTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTenDoUong1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNameCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlEastLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMoTa1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEastLayout.setVerticalGroup(
            pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEastLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenDoUong1)
                    .addComponent(txtNameCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaTien1)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMoTa1)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlNorth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlEast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlNorth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        FrmMain frm = new FrmMain();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int index = tblListCustomer.getSelectedRow();
        int rowcount = tblListCustomer.getSelectedRowCount();
        if (rowcount == 0) {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn khách hàng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (rowcount > 1) {
            JOptionPane.showMessageDialog(frame, "Vui lòng chỉ chọn một khách hàng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Object customerID = tblListCustomer.getValueAt(index, 0);
            String customerName = this.txtNameCustomer.getText();
            String customerSDT = this.txtPhoneNumber.getText();
            String customerNote = this.txtNote.getText();
            if (customerSDT.length() != 10 || cust.isInteger(customerSDT) == false) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin SĐT!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            cust.editCustomer(customerID, customerName, customerSDT, customerNote);
            btnLamMoiActionPerformed(evt);
            ShowData();
            JOptionPane.showMessageDialog(frame, "Đã cập nhật thông tin cho khách hàng \"" + customerName + "\"", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String notice = " ";
        int[] index = tblListCustomer.getSelectedRows();
        if (index.length == 0) {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn khách hàng", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            for (int i = 0; i < index.length; i++) {
                Object customerName = tblListCustomer.getValueAt(index[i], 1);
                if (i == index.length - 1) {
                    notice += "\"" + customerName + "\".";
                } else {
                    notice += "\"" + customerName + "\", ";
                }
            }
            int dialog = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin của \"" + notice + "\" ra khỏi danh sách ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialog == JOptionPane.YES_OPTION) {
                try {
                    for (int i = 0; i < index.length; i++) {
                        Object customerID = tblListCustomer.getValueAt(index[i], 0);
                        cust.deleteCustomer(customerID);
                        btnLamMoiActionPerformed(evt);
                    }
                    btnLamMoiActionPerformed(evt);
                    ShowData();
                    JOptionPane.showMessageDialog(frame, "Đã xóa thông tin của \"" + notice + "\" ra khỏi danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String customerName = this.txtNameCustomer.getText();
        String customerSDT = this.txtPhoneNumber.getText();
        String customerNote = this.txtNote.getText();

        if (customerName.isEmpty() || customerSDT.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (customerSDT.length() != 10 || cust.isInteger(customerSDT) == false) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin SĐT!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            cust.insertCustomer(customerName, customerSDT, customerNote);
            btnLamMoiActionPerformed(evt);
            ShowData();
        } catch (SQLException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.txtNameCustomer.setText(null);
        this.txtPhoneNumber.setText(null);
        this.txtNote.setText(null);
        btnThem.setEnabled(true);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        txtTimKiem.setText("");
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        txtTimKiem.setText("Tìm kiếm");
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String keyword = txtTimKiem.getText();
        DefaultTableModel tableModel = (DefaultTableModel) tblListCustomer.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(tableModel);
        tblListCustomer.setRowSorter(tr);

        tr.setStringConverter(new TableStringConverter() {
            @Override
            public String toString(TableModel model, int row, int column) {
                return VNCharacterUtils.removeAccent(model.getValueAt(row, column).toString()).toLowerCase();
            }
        });

        tr.setRowFilter(RowFilter.regexFilter(keyword));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblListCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListCustomerMouseClicked
        int index = this.tblListCustomer.getSelectedRow();
        String CustomerName = this.tblListCustomer.getValueAt(index, 1).toString();
        String CustomerSDT = this.tblListCustomer.getValueAt(index, 2).toString();
        String CustomerNote = this.tblListCustomer.getValueAt(index, 3).toString();
        this.txtNameCustomer.setText(CustomerName);
        this.txtPhoneNumber.setText(CustomerSDT);
        this.txtNote.setText(CustomerNote);
        btnThem.setEnabled(false);
    }//GEN-LAST:event_tblListCustomerMouseClicked

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
            java.util.logging.Logger.getLogger(FrmCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGiaTien1;
    private javax.swing.JLabel lblMoTa1;
    private javax.swing.JLabel lblTenDoUong1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlEast;
    private javax.swing.JPanel pnlNorth;
    public javax.swing.JTable tblListCustomer;
    private javax.swing.JTextField txtNameCustomer;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}