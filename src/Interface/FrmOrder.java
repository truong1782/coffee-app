/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import DTO.Table;
import Database.Connect;
import Process.DISCOUNT;
import Process.ORDER;
import Process.PRODUCT;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Acer
 */
public final class FrmOrder extends javax.swing.JFrame {

    PRODUCT prod = new PRODUCT();
    ORDER ord = new ORDER();
    Connect con = new Connect();
    DISCOUNT disc = new DISCOUNT();
    public int id;
    public int price;
    private Component frame;
    Locale locale = new Locale("vi", "VN");
    NumberFormat money_format = NumberFormat.getCurrencyInstance(locale);
    /**
     * Creates new form FrmBanHang_1
     */
    public FrmOrder() {
        initComponents();
        this.setExtendedState(FrmOrder.MAXIMIZED_BOTH);
        LoadTable();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            ShowData(prod.getAllProduct());
            tableContent();
        } catch (SQLException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void LoadTable() {
        pnlTable.removeAll();
        try {
            List<Table> listTable = ord.GetListTable();
            listTable.forEach((e) -> {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(119, 119));
                btn.setHorizontalTextPosition(JButton.CENTER);
                btn.setVerticalTextPosition(JButton.BOTTOM);
                btn.setFont(new java.awt.Font("Tahoma", 1, 14));
                btn.setText(e.getTableNumber());
                switch (e.getTableStatus()) {
                    case "Trống" ->  {
                        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bantrong.png")));
                        btn.setName(e.getTableStatus());
                    }
                    case "Đang phục vụ" ->  {
                        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cokhach.png")));
                        btn.setName(e.getTableStatus());
                    }
                    case "Đã Đặt" ->  {
                        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dadat.png")));
                        btn.setName(e.getTableStatus());
                    }
                }
//                if (e.getTableStatus().equals("Trống")) {
//                    btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bantrong.png")));
//                    btn.setName(e.getTableStatus());
//                }
//
//                if (e.getTableStatus().equals("Đang phục vụ")) {
//                    btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cokhach.png")));
//                    btn.setName(e.getTableStatus());
//                }
//
//                if (e.getTableStatus().equals("Đã Đặt")) {
//                    btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dadat.png")));
//                    btn.setName(e.getTableStatus());
//                }
                pnlTable.add(btn);
            });
            pnlTable.updateUI();
            Snumber.setEnabled(false);
            SetStatus();
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SetStatus() {
        Component[] components = pnlTable.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        loadStatus(button.getText(), button.getName());
                        if (button.getName().equals("Đang phục vụ")) {
                            btnDatBan.setEnabled(false);
                            btnGoiMon.setEnabled(true);
                            btnThanhToan.setEnabled(true);
                            tblMenu.setEnabled(true);
                            cmbDiscount.setEnabled(true);

                        }
                        if (button.getName().equals("Đã Đặt")) {
                            btnDatBan.setText("HỦY ĐẶT BÀN");
                            tblMenu.setEnabled(false);
                            btnGoiMon.setEnabled(false);
                        } else {
                            btnDatBan.setText("ĐẶT BÀN");
                        }
                        //Load du lieu bill_info
                        loadtblOrder(button.getText());
                        cmbDiscount.setSelectedIndex(0);
                    }
                });
            }
        }
    }

    //loadStatus
    public void loadStatus(String name, String status) {
        lblTableNumber.setText(name);
        lblStatus.setText("Tình trạng: " + status);
        btnDatBan.setEnabled(true);
        btnThanhToan.setEnabled(false);
        Snumber.setEnabled(false);
        btnXoaMon.setEnabled(false);
        tblMenu.setEnabled(true);
        btnGoiMon.setEnabled(true);
        cmbDiscount.setEnabled(false);
        total = 0;
    }

    public int total;
    public int reduce;

    //tblOrder
    public void loadtblOrder(String name) {
        try {
            total = 0;
            JTableHeader tableheader = tblOrdered.getTableHeader();
            tableheader.setFont(new Font("Tahoma", Font.BOLD, 16));
            DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer();
            textRenderer.setHorizontalAlignment(JLabel.LEFT);
            for (int i = 0; i < tblOrdered.getColumnCount(); i++) {
                tblOrdered.getColumnModel().getColumn(i).setCellRenderer(textRenderer);
            }
            ResultSet result = ord.getListBillInfo(name);
            DefaultTableModel tableModel = (DefaultTableModel) tblOrdered.getModel();
            tableModel.setRowCount(0); //clear table
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[3];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2
                rows[2] = money_format.format(result.getInt(3)); // lấy dữ liệu tai cột số 2 
                tableModel.addRow(rows); // đưa dòng dữ liệu vào tableModel
                total += result.getInt(3);
            }

            setStatusMoney();
        } catch (SQLException ex) {
        }
    }

    //set discount
    public void setStatusMoney() {
        lblTotalPrice.setText("Tổng tiền: " + total + " ₫");
        lblMoneyReduce.setText("Số tiền giảm: " + reduce + " ₫");
        int mfinal = (total - reduce);
        if (mfinal < 0) {
            mfinal = 0;
        }
        lblFinal.setText("Tổng tiền: " + mfinal + " ₫");
    }

    public void tableContent() throws SQLException {
        JTableHeader tableheader = tblMenu.getTableHeader();
        tableheader.setFont(new Font("Tahoma", Font.BOLD, 16));

        DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer();
        textRenderer.setHorizontalAlignment(JLabel.LEFT);

        for (int i = 0; i < tblMenu.getColumnCount(); i++) {
            tblMenu.getColumnModel().getColumn(i).setCellRenderer(textRenderer);
        }
        lblTrong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bantrong.png")));
        lblCoKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cokhach.png")));
        lblDaDat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dadat.png")));
        ResultSet result = disc.getAllDiscount();
        while (result.next()) {
            cmbDiscount.addItem(result.getString(2));
        }

    }

    public void ShowData(ResultSet result) throws SQLException {
        DefaultTableModel tableModel = (DefaultTableModel) tblMenu.getModel();
        tableModel.setRowCount(0); //clear table
        try {
            while (result.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[4];
                rows[0] = result.getString(2); // lấy dữ liệu tại cột số 1 
                rows[1] = money_format.format(result.getInt(3)); // lấy dữ liệu tai cột số 2 
                tableModel.addRow(rows); // đưa dòng dữ liệu vào tableModel
            }
        } catch (SQLException e) {
        }
    }

    //check hoa don trong
    public boolean checkBillEmpty() throws SQLException {
        ResultSet result = ord.getListBillInfo(lblTableNumber.getText());
        if (result.next()) {
            return false;
        }
        return true;
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
        pnlList = new javax.swing.JPanel();
        lblTrong = new javax.swing.JLabel();
        lblCoKhach = new javax.swing.JLabel();
        lblDaDat = new javax.swing.JLabel();
        pnlTable = new javax.swing.JPanel();
        pnlEast = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMenu = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnGoiMon = new javax.swing.JButton();
        lblNumber = new javax.swing.JLabel();
        Snumber = new javax.swing.JSpinner();
        pnlCenter = new javax.swing.JPanel();
        lblTableNumber = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        btnDatBan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrdered = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        lblFinal = new javax.swing.JLabel();
        lblMoneyReduce = new javax.swing.JLabel();
        lblCode = new javax.swing.JLabel();
        cmbDiscount = new javax.swing.JComboBox<>();
        btnXoaMon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

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
        jLabel1.setText("TRANG BÁN HÀNG");
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(1185, Short.MAX_VALUE))
                    .addComponent(jSeparator1)))
        );
        pnlNorthLayout.setVerticalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlNorth, java.awt.BorderLayout.NORTH);

        pnlList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách bàn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlList.setPreferredSize(new java.awt.Dimension(542, 693));

        lblTrong.setBackground(new java.awt.Color(51, 153, 0));
        lblTrong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTrong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrong.setText("Bàn trống");
        lblTrong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTrong.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblCoKhach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCoKhach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoKhach.setText("Có khách");
        lblCoKhach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCoKhach.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblDaDat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDaDat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDaDat.setText("Đã đặt ");
        lblDaDat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDaDat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        pnlTable.setForeground(new java.awt.Color(255, 255, 255));
        pnlTable.setAutoscrolls(true);
        pnlTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlTable.setPreferredSize(new java.awt.Dimension(540, 636));

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDaDat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCoKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDaDat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlList, java.awt.BorderLayout.WEST);

        pnlEast.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thực đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        tblMenu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Giá"
            }
        )
        {public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }}
    );
    tblMenu.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblMenuMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblMenu);

    txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

    btnGoiMon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btnGoiMon.setText("GỌI MÓN");
    btnGoiMon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnGoiMon.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnGoiMonActionPerformed(evt);
        }
    });

    lblNumber.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    lblNumber.setText("Số lượng:");

    Snumber.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
    Snumber.setEnabled(false);
    Snumber.setFocusCycleRoot(true);
    Snumber.setFocusable(false);

    javax.swing.GroupLayout pnlEastLayout = new javax.swing.GroupLayout(pnlEast);
    pnlEast.setLayout(pnlEastLayout);
    pnlEastLayout.setHorizontalGroup(
        pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlEastLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlEastLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(pnlEastLayout.createSequentialGroup()
                    .addComponent(lblNumber)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Snumber)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnGoiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(txtTimKiem))
            .addContainerGap())
    );
    pnlEastLayout.setVerticalGroup(
        pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEastLayout.createSequentialGroup()
            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(pnlEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnGoiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNumber)
                .addComponent(Snumber, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(17, 17, 17))
    );

    getContentPane().add(pnlEast, java.awt.BorderLayout.EAST);

    pnlCenter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin bàn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

    lblTableNumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

    lblStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

    btnDatBan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btnDatBan.setText("ĐẶT BÀN");
    btnDatBan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnDatBan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDatBanActionPerformed(evt);
        }
    });

    tblOrdered.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    tblOrdered.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {},
        new String [] {
            "Tên", "Số lượng", "Thành tiền"
        }

    )
    {
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    }
    );
    tblOrdered.setShowHorizontalLines(false);
    tblOrdered.setShowVerticalLines(false);
    tblOrdered.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblOrderedMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tblOrdered);

    jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel7.setText("CÁC MÓN ĐÃ GỌI");

    lblTotalPrice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    lblTotalPrice.setText("Tổng tiền:");

    btnThanhToan.setBackground(new java.awt.Color(153, 255, 153));
    btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    btnThanhToan.setText("THANH TOÁN");
    btnThanhToan.setEnabled(false);
    btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnThanhToanActionPerformed(evt);
        }
    });

    lblFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    lblFinal.setText("Tiền phải trả:");

    lblMoneyReduce.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    lblMoneyReduce.setText("Số tiền giảm:");

    lblCode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    lblCode.setText("Mã khuyến mãi:");

    cmbDiscount.setEnabled(false);
    cmbDiscount.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbDiscountActionPerformed(evt);
        }
    });

    btnXoaMon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btnXoaMon.setEnabled(false);
    btnXoaMon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnXoaMon.setLabel("XÓA MÓN");
    btnXoaMon.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnXoaMonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
    pnlCenter.setLayout(pnlCenterLayout);
    pnlCenterLayout.setHorizontalGroup(
        pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlCenterLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                    .addGap(164, 164, 164)
                    .addComponent(jLabel7)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlCenterLayout.createSequentialGroup()
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addComponent(lblCode)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbDiscount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(lblTableNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(btnDatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                            .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFinal)
                                .addComponent(lblMoneyReduce)
                                .addComponent(lblTotalPrice))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())))
    );
    pnlCenterLayout.setVerticalGroup(
        pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlCenterLayout.createSequentialGroup()
            .addComponent(lblTableNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnDatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(lblTotalPrice)
            .addGap(18, 18, 18)
            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCode)
                .addComponent(cmbDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(lblMoneyReduce)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
            .addComponent(lblFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        FrmMain frm = new FrmMain();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatBanActionPerformed
        if (btnDatBan.getText().equals("HỦY ĐẶT BÀN")) {
            ord.editStatus(lblTableNumber.getText(), "Trống");
            JOptionPane.showMessageDialog(frame, "Đã hủy đặt bàn \"" + lblTableNumber.getText() + "\" thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnDatBan.setText("ĐẶT BÀN");
            loadStatus(lblTableNumber.getText(), "Trống");
        } else {
            ord.editStatus(lblTableNumber.getText(), "Đã Đặt");
            JOptionPane.showMessageDialog(frame, "Đã đặt bàn \"" + lblTableNumber.getText() + "\" thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnDatBan.setText("HỦY ĐẶT BÀN");
            loadStatus(lblTableNumber.getText(), "Đã Đặt");
        }
        LoadTable();
    }//GEN-LAST:event_btnDatBanActionPerformed

    private void btnXoaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonActionPerformed
        try {
            ord.deleteProduct(lblTableNumber.getText(), idDelete);
            if (checkBillEmpty()) {
                ord.deleteBill(lblTableNumber.getText());
                loadStatus(lblTableNumber.getText(), "Trống");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(frame, "Đã xóa món thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        LoadTable();
        loadtblOrder(lblTableNumber.getText());
    }//GEN-LAST:event_btnXoaMonActionPerformed

    private void btnGoiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiMonActionPerformed
        int quantity = Integer.parseInt(Snumber.getValue().toString());
        int total = quantity * price;
        try {
            ord.insertBillInfo(lblTableNumber.getText(), id, quantity, total);
            JOptionPane.showMessageDialog(frame, "Đã thêm món thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            ord.editStatus(lblTableNumber.getText(), "Đang phục vụ");
            loadStatus(lblTableNumber.getText(), "Đang phục vụ");

        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadTable();
        loadtblOrder(lblTableNumber.getText());
    }//GEN-LAST:event_btnGoiMonActionPerformed

    private void tblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMenuMouseClicked
        int index = this.tblMenu.getSelectedRow();
        price = Integer.parseInt(this.tblMenu.getValueAt(index, 1).toString().replace("₫","").replace(".","").replaceAll("[\\s|\\u00A0]+", ""));
        id = prod.findIdProduct(this.tblMenu.getValueAt(index, 0).toString());
        Snumber.setEnabled(true);
    }//GEN-LAST:event_tblMenuMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        ord.Checkout(lblTableNumber.getText(), total, reduce, total - reduce);
        ord.editStatus(lblTableNumber.getText(), "Trống");
        JOptionPane.showMessageDialog(frame, "Đã thanh toán " + lblTableNumber.getText() + " thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        LoadTable();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    public int idDelete;
    private void tblOrderedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderedMouseClicked
        int index = this.tblOrdered.getSelectedRow();
        idDelete = prod.findIdProduct(this.tblOrdered.getValueAt(index, 0).toString());
        btnXoaMon.setEnabled(true);
    }//GEN-LAST:event_tblOrderedMouseClicked

    private void cmbDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDiscountActionPerformed
        try {
            reduce = disc.findDiscount(cmbDiscount.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadTable();
        setStatusMoney();
    }//GEN-LAST:event_cmbDiscountActionPerformed

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        txtTimKiem.setText("Tìm kiếm");
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        txtTimKiem.setText("");
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        try {
            String keyword = txtTimKiem.getText();
            ShowData(prod.searchProductsByKeyword(keyword));
        } catch (SQLException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

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
            java.util.logging.Logger.getLogger(FrmOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmOrder().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner Snumber;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDatBan;
    private javax.swing.JButton btnGoiMon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaMon;
    private javax.swing.JComboBox<String> cmbDiscount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCoKhach;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblDaDat;
    private javax.swing.JLabel lblFinal;
    private javax.swing.JLabel lblMoneyReduce;
    private javax.swing.JLabel lblNumber;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTableNumber;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel lblTrong;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlEast;
    private javax.swing.JPanel pnlList;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JTable tblMenu;
    private javax.swing.JTable tblOrdered;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
