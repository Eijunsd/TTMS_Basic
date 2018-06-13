package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Seat;

import java.util.ArrayList;

public interface ISaleDAO {
    public int getAllCount();

    public int getAllPageCount();

    public int getCurrentPage();

    public ArrayList<Seat> findSeatByPage(int cPage, String seat_row);

    public boolean insert(Seat seat);

    public boolean delete(int seatId);

    public boolean update(Seat seat);

    public Seat selectByMany(int studioid, int seat_row, int seat_column);

    public ArrayList<Seat> selectByStudio(int studioid);

    public Seat findSeatById(int seatId);

    public ArrayList<Seat> findSeatAll();

}
