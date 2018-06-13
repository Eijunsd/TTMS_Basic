package cn.xupt.ttms.dao;

import cn.xupt.ttms.idao.IPlayDAO;
import cn.xupt.ttms.model.Play;
import cn.xupt.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayDAO implements IPlayDAO {
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

    @SuppressWarnings("finally")
    public ArrayList<Play> getPlayByPage(int cPage, String play_id) {
        // TODO Auto-generated method stub
        currentPage = cPage;
        ArrayList<Play> list = new ArrayList<Play>();

        // ��δָ������ĳ�ˣ���Ĭ��ȫ��
        if (null == play_id || play_id.equals("null")) {
            play_id = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ��¼���� as AllRecord������

            String sql1 = "select count(play_id) as AllRecord from play where play_id like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + play_id + "%");
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
            String sql2 = "select * from play where play_id like ? limit ?,?";
            // select * from tablename limit ��ʼλ��,ÿҳ����

            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + play_id + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            System.out.println(pstmt);
            rs = pstmt.executeQuery();
            Play play = null;
            //
            while (rs.next()) {
                play = new Play();
                play.setPlayId(rs.getInt("play_id"));
                play.setPlayType(rs.getString("play_type"));
                play.setPlayLang(rs.getString("play_lang"));
                play.setPlayName(rs.getString("play_name"));
                play.setPlayIntroduction(rs.getString("play_introduction"));
                play.setPlayImage(rs.getString("play_image"));
                play.setPlayTicketPrice(rs.getFloat("play_ticket_price"));
                play.setPlayLength(rs.getInt("play_length"));
                play.setPlayStatus(rs.getInt("play_status"));

                // �����û���Ϣ�����б�
                list.add(play);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

    @Override
    public boolean insert(Play play) {
        // TODO Auto-generated method stub

        boolean result = false;
        if (play == null)
            return result;

        // ��ȡConnection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into play (play_type, play_lang, play_name, play_introduction, play_image, play_length, play_ticket_price, play_status) values (?,?,?,?,?,?,?);";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, play.getPlayType());
            pstmt.setString(2, play.getPlayLang());
            pstmt.setString(3, play.getPlayName());
            pstmt.setString(4, play.getPlayIntroduction());
            pstmt.setString(5, play.getPlayImage());
            pstmt.setString(6, String.valueOf(play.getPlayLength()));
            pstmt.setString(7, String.valueOf(play.getPlayTicketPrice()));
            pstmt.setString(7, String.valueOf(play.getPlayStatus()));


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

    @Override
    public boolean delete(int playId) {
        // TODO Auto-generated method stub

        boolean result = false;
        if (playId == 0)
            return result;
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            // ɾ����ĳ���û�
            String sql = "delete from play where play_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, playId);
            System.out.println(pstmt);
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

    @Override
    public boolean update(Play play) {
        boolean result = false;
        if (play == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "update play set play_type = ?, play_lang=?, play_name=?,play_introduction=?,play_image=?," +
                    "play_length=?, play_ticket_price=?, play_status=?  where play_id=?;";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, play.getPlayType());
            pstmt.setString(2, play.getPlayLang());
            pstmt.setString(3, play.getPlayName());
            pstmt.setString(4, play.getPlayIntroduction());
            pstmt.setString(5, play.getPlayImage());
            pstmt.setString(6, String.valueOf(play.getPlayLength()));
            pstmt.setString(7, String.valueOf(play.getPlayTicketPrice()));
            pstmt.setString(8, String.valueOf(play.getPlayStatus()));
            pstmt.setString(9, String.valueOf(play.getPlayId()));

            System.out.println(pstmt);

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

    @Override
    public ArrayList<Play> findPlayAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Play> findPlayByName(String playName) {
        // TODO Auto-generated method stub
        if (playName == null || playName.equals(""))
            return null;

        ArrayList<Play> list = new ArrayList<Play>();
        Play info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����:ģ����ѯ
            pstmt = con.prepareStatement("select * from play where play_name like ?");
            pstmt.setString(1, "%" + playName + "%");// ƴ��ģ����ѯ��
            System.out.println(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // �����ֵ�Ļ���ʵ����
                info = new Play();
                info.setPlayId(rs.getInt("play_id"));
                info.setPlayType(rs.getString("play_type"));
                info.setPlayLang(rs.getString("play_lang"));
                info.setPlayName(rs.getString("play_name"));
                info.setPlayIntroduction(rs.getString("play_introduction"));
                info.setPlayImage(rs.getString("play_inage"));
                info.setPlayLength(rs.getInt("play_length"));
                info.setPlayTicketPrice(rs.getFloat("play_ticket_price"));
                info.setPlayStatus(rs.getInt("play_status"));

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

    @Override
    public Play findPlayById(int playId) {
        // TODO Auto-generated method stub

        Play info = null;
        if (playId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from play where play_id=?");
            pstmt.setInt(1, playId);
            System.out.println(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // �����ֵ�Ļ���ʵ����
                info = new Play();
                info.setPlayId(rs.getInt("play_id"));
                info.setPlayType(rs.getString("play_type"));
                info.setPlayLang(rs.getString("play_lang"));
                info.setPlayName(rs.getString("play_name"));
                info.setPlayIntroduction(rs.getString("play_introduction"));
                info.setPlayImage(rs.getString("play_image"));
                info.setPlayLength(rs.getInt("play_length"));
                info.setPlayTicketPrice(rs.getFloat("play_ticket_price"));
                info.setPlayStatus(rs.getInt("play_status"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

}
