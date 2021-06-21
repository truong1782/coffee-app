/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Database.Connect;
import Process.ORDER;
import Process.PRODUCT;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class FrmMenu extends javax.swing.JFrame {

    /**
     * Creates new form FrmQuanLyThucUong
     */
    PRODUCT prod = new PRODUCT();
    ORDER ord = new ORDER();
    Connect con = new Connect();
    private Component frame;
    Locale locale = new Locale("vi", "VN");
    NumberFormat money_format = NumberFormat.getCurrencyInstance(locale);

    public FrmMenu() {
        initComponents();
        this.setExtendedState(FrmMenu.MAXIMIZED_BOTH);
        tableContent();
        try {
            ShowData(prod.getAllProduct());
        } catch (SQLException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tableContent() {
        JTableHeader tableheader = tblDanhSachDoUong.getTableHeader();
        tableheader.setFont(new Font("Tahoma", Font.BOLD, 16));

        DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer();
        textRenderer.setHorizontalAlignment(JLabel.LEFT);

        for (int i = 0; i < tblDanhSachDoUong.getColumnCount(); i++) {
            tblDanhSachDoUong.getColumnModel().getColumn(i).setCellRenderer(textRenderer);
        }
    }

    public void ShowData(ResultSet result) throws SQLException {
        DefaultTableModel tableModel = (DefaultTableModel) tblDanhSachDoUong.getModel();
        tableModel.setRowCount(0); //clear table
        try {
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[4];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 
                rows[2] = money_format.format(result.getInt(3)); // lấy dữ liệu tai cột số 3
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
        tblDanhSachDoUong = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        pnlEast = new javax.swing.JPanel();
        lblTenDoUong1 = new javax.swing.JLabel();
        txtTenDoUong = new javax.swing.JTextField();
        lblGiaTien1 = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        lblMoTa1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtareaGhiChu = new javax.swing.JTextArea();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1535, 872));

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
        jLabel1.setText("TRANG QUẢN LÝ ĐỒ UỐNG");
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
                        .addContainerGap(1058, Short.MAX_VALUE))
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

        getContentPane().add(pnlNorth, java.awt.BorderLayout.NORTH);

        pnlCenter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đồ uống", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jScrollPane2.setAlignmentX(2.0F);
        jScrollPane2.setAlignmentY(2.0F);

        tblDanhSachDoUong.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblDanhSachDoUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đồ uống", "Tên đồ uống", "Giá tiền", "Ghi chú"
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
        tblDanhSachDoUong.setShowGrid(true);
        tblDanhSachDoUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachDoUongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSachDoUong);

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        pnlEast.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đồ uống", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        lblTenDoUong1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTenDoUong1.setText("Tên đồ uống:");

        txtTenDoUong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lblGiaTien1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblGiaTien1.setText("Giá tiền:");

        txtGiaTien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lblMoTa1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblMoTa1.setText("Ghi chú:");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtareaGhiChu.setColumns(20);
        txtareaGhiChu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtareaGhiChu.setLineWrap(true);
        txtareaGhiChu.setRows(5);
        txtareaGhiChu.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(txtareaGhiChu);

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
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
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
                .addGap(10, 10, 10)
                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEastLayout.createSequentialGroup()
                        .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGiaTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenDoUong1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenDoUong)
                            .addComponent(txtGiaTien)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEastLayout.createSequentialGroup()
                        .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMoTa1)
                            .addGroup(pnlEastLayout.createSequentialGroup()
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEastLayout.setVerticalGroup(
            pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEastLayout.createSequentialGroup()
                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenDoUong1)
                    .addComponent(txtTenDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaTien1)
                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMoTa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 396, Short.MAX_VALUE))
        );

        getContentPane().add(pnlEast, java.awt.BorderLayout.EAST);
        pnlEast.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String productName = this.txtTenDoUong.getText();
        String productPrice = this.txtGiaTien.getText();
        String productNote = this.txtareaGhiChu.getText();
        if (prod.isInteger(productPrice) == false) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập số vào ô giá tiền!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (productName.isEmpty() || productPrice.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                prod.insertProduct(productName, productPrice, productNote);
                btnLamMoiActionPerformed(evt);
                ShowData(prod.getAllProduct());
            } catch (SQLException ex) {
                Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int[] index = tblDanhSachDoUong.getSelectedRows();
        String notice = " ";
        if (index.length == 0) {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn đồ uống", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            for (int i = 0; i < index.length; i++) {
                Object productName = tblDanhSachDoUong.getValueAt(index[i], 1);
                if (i == index.length - 1) {
                    notice += "\"" + productName + "\".";
                } else {
                    notice += "\"" + productName + "\", ";
                }
            }
            int dialog = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa đồ uống " + notice + " ra khỏi danh sách ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialog == JOptionPane.YES_OPTION) {
                try {
                    for (int i = 0; i < index.length; i++) {
                        Object productID = tblDanhSachDoUong.getValueAt(index[i], 0);
                        System.out.print(Integer.parseInt(tblDanhSachDoUong.getValueAt(index[i], 0).toString()));
                        prod.deleteProduct(productID);
                        btnLamMoiActionPerformed(evt);
                    }
                    ShowData(prod.getAllProduct());
                    JOptionPane.showMessageDialog(frame, "Đã xóa đồ uống: " + notice + " ra khỏi danh sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.txtTenDoUong.setText(null);
        this.txtGiaTien.setText(null);
        this.txtareaGhiChu.setText(null);
        btnThem.setEnabled(true);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int index = tblDanhSachDoUong.getSelectedRow();
        int rowcount = tblDanhSachDoUong.getSelectedRowCount();
        if (rowcount == 0) {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn đồ uống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (rowcount > 1) {
            JOptionPane.showMessageDialog(frame, "Vui lòng chỉ chọn một đồ uống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Object productID = tblDanhSachDoUong.getValueAt(index, 0);
            String productName = this.txtTenDoUong.getText();
            String productPrice = this.txtGiaTien.getText();
            String productNote = this.txtareaGhiChu.getText();
            if (prod.isInteger(productPrice) == false) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập số vào ô giá tiền!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            prod.editProduct(productID, productName, productPrice, productNote);
            btnLamMoiActionPerformed(evt);
            ShowData(prod.getAllProduct());
            JOptionPane.showMessageDialog(frame, "Đã cập nhật thông tin cho đồ uống \"" + productName + "\"", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblDanhSachDoUongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachDoUongMouseClicked
        int index = this.tblDanhSachDoUong.getSelectedRow();
        String ProductName = this.tblDanhSachDoUong.getValueAt(index, 1).toString();
        String ProductPrice = String.valueOf(this.tblDanhSachDoUong.getValueAt(index, 2)).replace("₫","").replace(".","");
        ProductPrice = ProductPrice.replaceAll("[\\s|\\u00A0]+", "");
        String ProductNote = this.tblDanhSachDoUong.getValueAt(index, 3).toString();
        this.txtTenDoUong.setText(ProductName);
        this.txtGiaTien.setText(ProductPrice);
        this.txtareaGhiChu.setText(ProductNote);
        btnThem.setEnabled(false);
    }//GEN-LAST:event_tblDanhSachDoUongMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        try {
            String keyword = txtTimKiem.getText();
            ShowData(prod.searchProductsByKeyword(keyword));
        } catch (SQLException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        txtTimKiem.setText("");
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        txtTimKiem.setText("Tìm kiếm");
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        FrmMain frm = new FrmMain();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmMenu().setVisible(true);
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
    public javax.swing.JTable tblDanhSachDoUong;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtTenDoUong;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextArea txtareaGhiChu;
    // End of variables declaration//GEN-END:variables

}