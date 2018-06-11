package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Seat;

import java.util.ArrayList;

public interface ISeatDAO {
    // ��
    public boolean insert(Seat seat);

    // ɾ
    public boolean delete(int seatId);

    // ��
    public boolean update(Seat seat);

    // �������û�(һ�����ںͽ��潻��)
    public ArrayList<Seat> findSeatAll();

    public Seat findSeatById(int seatNo);

    public ArrayList<Seat> selectByStudio(int studioid);

    public Seat selectByMany(int studioid, int seat_row, int seat_column);

    public ArrayList<Seat> findSeatByPage(int cPage, String seat_row);
}
