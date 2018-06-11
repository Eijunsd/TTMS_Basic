package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Seat;

import java.util.ArrayList;

public interface ISeatDAO {
    // 增
    public boolean insert(Seat seat);

    // 删
    public boolean delete(int seatId);

    // 改
    public boolean update(Seat seat);

    // 查所有用户(一般用于和界面交互)
    public ArrayList<Seat> findSeatAll();

    public Seat findSeatById(int seatNo);

    public ArrayList<Seat> selectByStudio(int studioid);

    public Seat selectByMany(int studioid, int seat_row, int seat_column);

    public ArrayList<Seat> findSeatByPage(int cPage, String seat_row);
}
