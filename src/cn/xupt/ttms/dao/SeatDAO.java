package cn.xupt.ttms.dao;

import cn.xupt.ttms.idao.ISeatDAO;
import cn.xupt.ttms.model.Seat;
import cn.xupt.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatDAO implements ISeatDAO {
    public static final int PAGE_SIZE = 10; // ÿҳ��ʾ����
    private int allCount; // ���ݿ�������
    private int allPageCount; // ��ҳ��
    private int currentPage; // ��ǰҳ

    public int getAllCount() {
        return allCount;
    }

    public int getAllPageCount() {
        return allPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param cPage
     * @param seat_row
     * @return
     */
    @SuppressWarnings("finally")
    public ArrayList<Seat> findSeatByPage(int cPage, String seat_row) {
        currentPage = cPage;
        ArrayList<Seat> list = new ArrayList<Seat>();


        if (null == seat_row || seat_row.equals("null")) {
            seat_row = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ��¼���� as AllRecord������

            String sql1 = "select count(studio_id) as AllRecord from Seat where seat_row like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + seat_row + "%");
            rs = pstmt.executeQuery();
            // allCount; ���ݿ�������
            if (rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            pstmt.close();

            // ������ҳ�� PAGE_SIZE = 5; // ÿҳ��ʾ����
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;

            // �����ǰҳ��������ҳ������ֵΪ��ҳ��
            if (allPageCount > 0 && currentPage > allPageCount)
                currentPage = allPageCount;

            // ��ȡ��currentPageҳ����
            String sql2 = "select * from Seat where seat_row like ? limit ?,?";
            // select * from tablename limit ��ʼλ��,ÿҳ����
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + seat_row + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            Seat seat = null;
            while (rs.next()) {
                seat = new Seat();
                seat.setSeatId(rs.getInt("seat_id"));
                seat.setStudioId(rs.getInt("studio_id"));
                seat.setSeatRow(rs.getInt("seat_row"));
                seat.setSeatColumn(rs.getInt("seat_column"));
                seat.setSeatStatus(rs.getInt("seat_status"));

                // �����û���Ϣ�����б�
                list.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

    @SuppressWarnings("finally")
    public boolean insert(Seat seat) {
        boolean result = false;
        if (seat == null)
            return result;

        // ��ȡConnection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into seat(studio_id, seat_row, seat_column, seat_status, emp_email) values(?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seat.getStudioId());
            pstmt.setInt(2, seat.getSeatRow());
            pstmt.setInt(3, seat.getSeatColumn());
            pstmt.setInt(4, seat.getSeatStatus());
            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // �ر�����
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * ɾ���û�(ͨ��seatId)
     *
     * @return �ɹ����boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int seatId) {
        boolean result = false;
        if (seatId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            // ɾ����ĳ���û�
            String sql = "delete from seat where seat_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seatId);
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // �ر�����
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * �޸��û���Ϣ
     *
     * @return �ɹ����boolean
     */
    @SuppressWarnings("finally")
    public boolean update(Seat seat) {
        boolean result = false;
        if (seat == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "update seat set studio_id=?,seat_row=?,seat_column=?,seat_status=? where seat_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seat.getStudioId());
            pstmt.setInt(2, seat.getSeatRow());
            pstmt.setInt(3, seat.getSeatColumn());
            pstmt.setInt(4, seat.getSeatStatus());
            pstmt.setInt(5, seat.getSeatId());

            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // �ر�����
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * ��ȡ������λ��Ϣ(һ�����ںͽ��潻��)
     *
     * @return Seat�б�
     */
    @SuppressWarnings("finally")
    public ArrayList<Seat> findSeatAll() {
        ArrayList<Seat> list = new ArrayList<Seat>();
        Seat info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from seat");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Seat();

                info.setSeatId(rs.getInt("seat_id"));
                info.setStudioId(rs.getInt("studio_id"));
                info.setSeatRow(rs.getInt("seat_row"));
                info.setSeatColumn(rs.getInt("seat_column"));
                info.setSeatStatus(rs.getInt("seat_status"));

                // �����б�
                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    /**
     * ����seat_id��ȡ�û���Ϣ(һ�����������ڲ���������)
     *
     * @return �û�
     */
    @SuppressWarnings("finally")
    public Seat findSeatById(int seatId) {
        Seat info = null;
        if (seatId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ������λ����

            pstmt = con.prepareStatement("select * from seat where seat_id=?");
            pstmt.setInt(1, seatId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // �����ֵ��ʵ����
                info = new Seat();
                info.setSeatId(seatId);
                info.setStudioId(rs.getInt("studio_id"));
                info.setSeatRow(rs.getInt("seat_row"));
                info.setSeatColumn(rs.getInt("seat_column"));
                info.setSeatStatus(rs.getInt("seat_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

    public ArrayList<Seat> selectByStudio(int studioid) {

        ArrayList<Seat> studioList = new ArrayList<Seat>();
        Seat info = null;
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from seat where studio_id=?");
            pstmt.setInt(1, studioid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Seat();

                info.setSeatId(rs.getInt("seat_id"));
                info.setStudioId(rs.getInt("studio_id"));
                info.setSeatRow(rs.getInt("seat_row"));
                info.setSeatColumn(rs.getInt("seat_column"));
                info.setSeatStatus(rs.getInt("seat_status"));

                // �����б�
                studioList.add(info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return studioList;
        }
    }

    @Override
    public Seat selectByMany(int studioid, int seat_row, int seat_column) {
        Seat info = null;
        if (studioid == 0 && seat_row == 0 && seat_column == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����

            pstmt = con.prepareStatement("select * from seat where studio_id=? AND seat_row=? AND seat_column=?");
            pstmt.setInt(1, studioid);
            pstmt.setInt(2, seat_row);
            pstmt.setInt(3, seat_column);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // �����ֵ�Ļ���ʵ����
                info = new Seat();
                info.setSeatId(rs.getInt("seat_id"));
                info.setStudioId(rs.getInt("studio_id"));
                info.setSeatRow(rs.getInt("seat_row"));
                info.setSeatColumn(rs.getInt("seat_column"));
                info.setSeatStatus(rs.getInt("seat_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }
}
