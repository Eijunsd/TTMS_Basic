package cn.xupt.ttms.dao;

import cn.xupt.ttms.idao.IStudioDAO;
import cn.xupt.ttms.model.Studio;
import cn.xupt.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudioDAO implements IStudioDAO {


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


//    @SuppressWarnings("finally")
//    public ArrayList<Studio> findStudioByPage(int cPage) {
////        System.out.println("��StudioDAO�е���findStudioByPage��ʼ...");
//        currentPage = cPage;
//        ArrayList<Studio> list = new ArrayList<Studio>();
//
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            // ��ȡ��¼���� as AllRecord������
//
//            String sql1 = "select count(studio_id) as AllRecord from studio ";
//            conn = ConnectionManager.getInstance().getConnection();
//            pstmt = conn.prepareStatement(sql1);
//            rs = pstmt.executeQuery();
//            // allCount; ���ݿ�������
//            if (rs.next())
//                allCount = rs.getInt("AllRecord");
//            rs.close();
//            pstmt.close();
//
//            // ������ҳ�� PAGE_SIZE = 5; // ÿҳ��ʾ����
//            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;
//
//            // �����ǰҳ��������ҳ������ֵΪ��ҳ��
//            if (allPageCount > 0 && currentPage > allPageCount)
//                currentPage = allPageCount;
//
//            // ��ȡ��currentPageҳ����
//            String sql2 = "select * from studio where studio.studio_row_count  limit ?,?";
//            // select * from tablename limit ��ʼλ��,ÿҳ����
//            pstmt = conn.prepareStatement(sql2);
//            pstmt.setInt(1, PAGE_SIZE * (currentPage - 1));
//            pstmt.setInt(2, PAGE_SIZE);
//            rs = pstmt.executeQuery();
//            Studio studio = null;
//
//            while (rs.next()) {
//                studio = new Studio();
//                studio.setStudioId(rs.getInt("studio_id"));
//                studio.setStudioName(rs.getString("studio_name"));
//                studio.setStudioRowCount(rs.getInt("studio_row_count"));
//                studio.setStudioColCount(rs.getInt("studio_col_count"));
//                studio.setStudioFlag(rs.getInt("studio_flag"));
////                System.out.println(studio);
//                // �����û���Ϣ�����б�
//                list.add(studio);
//            }
////            System.out.println("��StudioDAO�е���findStudioByPage����...");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            ConnectionManager.close(rs, pstmt, conn);
//            return list;
//        }
//    }

//    @SuppressWarnings("finally")
//    public ArrayList<Studio> findStudioByPage(int cPage, String seat_row) {
//        currentPage = cPage;
//        ArrayList<Studio> list = new ArrayList<Studio>();
//
//
//        if (null == seat_row || seat_row.equals("null")) {
//            seat_row = "";
//        }
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            // ��ȡ��¼���� as AllRecord������
//
//            String sql1 = "select count(studio_id) as AllRecord from studio where studio.studio_col_count like ?";
//            conn = ConnectionManager.getInstance().getConnection();
//            pstmt = conn.prepareStatement(sql1);
//            pstmt.setString(1, "%" + seat_row + "%");
//            rs = pstmt.executeQuery();
//            // allCount; ���ݿ�������
//            if (rs.next())
//                allCount = rs.getInt("AllRecord");
//            rs.close();
//            pstmt.close();
//
//            // ������ҳ�� PAGE_SIZE = 5; // ÿҳ��ʾ����
//            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;
//
//            // �����ǰҳ��������ҳ������ֵΪ��ҳ��
//            if (allPageCount > 0 && currentPage > allPageCount)
//                currentPage = allPageCount;
//
//            // ��ȡ��currentPageҳ����
//            String sql2 = "select * from studio where studio.studio_row_count like ? limit ?,?";
//            // select * from tablename limit ��ʼλ��,ÿҳ����
//            pstmt = conn.prepareStatement(sql2);
//            pstmt.setString(1, "%" + seat_row + "%");
//            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
//            pstmt.setInt(3, PAGE_SIZE);
//            rs = pstmt.executeQuery();
//            Studio studio = null;
//
//            while (rs.next()) {
//                studio = new Studio();
//                studio.setStudioId(rs.getInt("studio_id"));
//
//                // �����û���Ϣ�����б�
//                list.add(studio);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            ConnectionManager.close(rs, pstmt, conn);
//            return list;
//        }
//    }

//    public ArrayList<Studio> getStudioByPage(int cPage, String studioId) {
//
//        currentPage = cPage;
//        ArrayList<Studio> list = new ArrayList<Studio>();
//
//        // ��δָ������ĳ�ˣ���Ĭ��ȫ��
//        if (null == studioId || studioId.equals("null")) {
//            studioId = "";
//        }
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            // ��ȡ��¼���� as AllRecord������
//
//            String sql1 = "select count(tudio_id) as AllRecord from studio where studioId like ?";
//            conn = ConnectionManager.getInstance().getConnection();
//            pstmt = conn.prepareStatement(sql1);
//            pstmt.setString(1, "%" + studioId + "%");
//            rs = pstmt.executeQuery();
//            // allCount; ���ݿ�������
//            if (rs.next())
//                allCount = rs.getInt("AllRecord");
//            rs.close();
//            pstmt.close();
//
//            // ������ҳ�� PAGE_SIZE = 5; // ÿҳ��ʾ����
//            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;
//
//            // �����ǰҳ��������ҳ������ֵΪ��ҳ��
//            if (allPageCount > 0 && currentPage > allPageCount)
//                currentPage = allPageCount;
//
//            // ��ȡ��currentPageҳ����
//            String sql2 = "select * from studio where studio_id like ? limit ?,?";
//            // select * from tablename limit ��ʼλ��,ÿҳ����
//
//            pstmt = conn.prepareStatement(sql2);
//            pstmt.setString(1, "%" + studioId + "%");
//            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
//            pstmt.setInt(3, PAGE_SIZE);
//            System.out.println(pstmt);
//            rs = pstmt.executeQuery();
//            Studio studio = null;
//            //
//            while (rs.next()) {
//                studio = new Studio();
//                studio.setStudioId(rs.getInt("studio_id"));
//                studio.setStudioName(rs.getString("studio_name"));
//                studio.setStudioRowCount(rs.getInt("studio_row_cout"));
//                studio.setStudioColCount(rs.getInt("studio_col_count"));
//                studio.setStudioIntroduction(rs.getString("studio_introduction"));
//                studio.setStudioFlag(rs.getInt("studio_flag"));
//                // �����û���Ϣ�����б�
//                list.add(studio);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            ConnectionManager.close(rs, pstmt, conn);
//            return list;
//        }
//
//    }

    @SuppressWarnings("finally")
    public boolean insert(Studio studio) {
        boolean result = false;
        if (studio == null)
            return result;

        // ��ȡConnection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into studio(studio_name, studio_row_count, studio_col_count, studio_introduction,studio_flag) values(?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, studio.getStudioId());
            pstmt.setString(1, studio.getStudioName());
            pstmt.setInt(2, studio.getStudioRowCount());
            pstmt.setInt(3, studio.getStudioColCount());
            pstmt.setString(4, studio.getStudioIntroduction());
            pstmt.setInt(5, studio.getStudioFlag());

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
     * ɾ���û�(ͨ��studioId)
     *
     * @return �ɹ����boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int studioId) {
        boolean result = false;
        if (studioId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            // ɾ����ĳ���û�
            System.out.println(studioId);
            String sql = "delete from studio where studio_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studioId);
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
    public boolean update(Studio studio) {
        boolean result = false;
        if (studio == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "update studio set studio_name=?,studio_row_count=?,studio_col_count=?,studio_introduction=?,studio_flag=? where studio_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studio.getStudioName());
            pstmt.setInt(2, studio.getStudioRowCount());
            pstmt.setInt(3, studio.getStudioColCount());
            pstmt.setString(4, studio.getStudioIntroduction());
            pstmt.setInt(5, studio.getStudioFlag());
            pstmt.setInt(6, studio.getStudioId());

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
     * ��ȡ�����û���Ϣ(һ�����ںͽ��潻��)
     *
     * @return Studio�б�
     */
    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioAll() {
        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from studio");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Studio();
                info.setStudioId(rs.getInt("studio_id"));
                info.setStudioName(rs.getString("studio_name"));
                info.setStudioRowCount(rs.getInt("studio_row_count"));
                info.setStudioColCount(rs.getInt("studio_col_count"));
                info.setStudioIntroduction(rs.getString("studio_introduction"));
                info.setStudioFlag(rs.getInt("studio_flag"));
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
     * �����û�����ȡ�û���Ϣ(һ�����ںͽ��潻��)
     *
     * @return Studio�б�
     */
    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioByName(String studioName) {
        if (studioName == null || studioName.equals(""))
            return null;

        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����:ģ����ѯ
            pstmt = con.prepareStatement("select * from studio where studio_name like ?");
            pstmt.setString(1, "%" + studioName + "%");// ƴ��ģ����ѯ��
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // �����ֵ�Ļ���ʵ����
                info = new Studio();
                info.setStudioId(rs.getInt("studio_id"));
                info.setStudioName(rs.getString("studio_name"));
                info.setStudioRowCount(rs.getInt("studio_row_count"));
                info.setStudioColCount(rs.getInt("studio_col_count"));
                info.setStudioIntroduction(rs.getString("studio_introduction"));
                info.setStudioFlag(rs.getInt("studio_flag"));
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
     * ����studio_id��ȡ�û���Ϣ(һ�����������ڲ���������)
     *
     * @return �û�
     */
    @SuppressWarnings("finally")
    public Studio findStudioById(int studioId) {
        Studio info = null;
        if (studioId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from studio where studio_id=?");
            pstmt.setInt(1, studioId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // �����ֵ�Ļ���ʵ����
                info = new Studio();
                info.setStudioId(rs.getInt("studio_id"));
                info.setStudioName(rs.getString("studio_name"));
                info.setStudioRowCount(rs.getInt("studio_row_count"));
                info.setStudioColCount(rs.getInt("studio_col_count"));
                info.setStudioIntroduction(rs.getString("studio_introduction"));
                info.setStudioFlag(rs.getInt("studio_flag"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

    @Override
    public ArrayList<Studio> findStudioByPage(int cPage, String studioId) {
        currentPage = cPage;
        ArrayList<Studio> list = new ArrayList<Studio>();

        // ��δָ������ĳ�ˣ���Ĭ��ȫ��
        if (null == studioId || studioId.equals("null")) {
            studioId = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ��¼���� as AllRecord������

            String sql1 = "select count(studio_id) as AllRecord from studio where studio.studio_id like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + studioId + "%");
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
            String sql2 = "select * from studio where studio_id like ? limit ?,?";
            // select * from tablename limit ��ʼλ��,ÿҳ����

            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + studioId + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            System.out.println(pstmt);
            rs = pstmt.executeQuery();
            Studio studio = null;
            //
            while (rs.next()) {
                studio = new Studio();
                studio.setStudioId(rs.getInt("studio_id"));
                studio.setStudioName(rs.getString("studio_name"));
                studio.setStudioRowCount(rs.getInt("studio_row_count"));
                studio.setStudioColCount(rs.getInt("studio_col_count"));
                studio.setStudioIntroduction(rs.getString("studio_introduction"));
                studio.setStudioFlag(rs.getInt("studio_flag"));
                // �����û���Ϣ�����б�
                list.add(studio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }
}
