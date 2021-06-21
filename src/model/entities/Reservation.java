package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	
	public Reservation() {
		
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
		if (!checkOut.after(checkIn)) { // SEMPRE BOM TRATAR AS EXCECOES NO COMECO DOS METODOS (PROGRAMAÇAO DEFENSIVA)
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); // É A FORMULA COMUM PARA CALCULAR A DIFERENÇA ENTRE DATA. OBS: O CÁLCULO É FEITO EM MILISEGUNDOS.
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) throws DomainException { //VOU PROPAGAR A NOVA EXCECAO NA CLASSE RESERVATION E TRATA-LA NA PAGINA PRINCIPAL DO PROGRAMA 
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) { // MÉTODO USADO PARA VER SE AS DATAS EM QUESTÃO SÃO ANTERIORES A DATA ATUAL.
			throw new DomainException("Reservation dates for update must be future dates"); // A EXCEÇÃO ILLEGALARGUMENTEXCEPTION É USADA QUANDO OS ARGUMENTOS PASSADOS PARA O MÉTODO SÃO INVÁLIDOS
		}
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}
	
	@Override
	public String toString() {
		return "Room: "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration() 
				+ " nights";
	}
}
