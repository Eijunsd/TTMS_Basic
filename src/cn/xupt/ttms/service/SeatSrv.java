package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.ISeatDAO;
import cn.xupt.ttms.model.Seat;

import java.util.ArrayList;

public class SeatSrv {
    private ISeatDAO seatDAO = DAOFactory.createSeatDAO();

    // ��
    public boolean insert(Seat seat){
        return seatDAO.insert(seat);
    }

    // ɾ
    public boolean delete(int seatId){
        return seatDAO.delete(seatId);
    }

    // ��
    public boolean update(Seat seat){
        return seatDAO.update(seat);
    }

    // �������û�(һ�����ںͽ��潻��)
    public ArrayList<Seat> findSeatAll(){
        return seatDAO.findSeatAll();
    }

    public Seat findSeatById(int seatNo){
        return seatDAO.findSeatById(seatNo);
    }

    public ArrayList<Seat> selectByStudio(int studioid){
        return seatDAO.selectByStudio(studioid);
    }

    public Seat selectByMany(int studioid, int seat_row, int seat_column){
        return seatDAO.selectByMany(studioid, seat_row, seat_column);
    }

    public ArrayList<Seat> findSeatByPage(int cPage, String seat_row){
        return seatDAO.findSeatByPage(cPage, seat_row);
    }
}
