package presentacion;

public class Events {

	public static final int ALTA_TRABAJADOR_TIEMPO_PARCIAL = 101;
	public static final int ALTA_TRABAJADOR_TIEMPO_COMPLETO = 102;
	public static final int BUSCAR_TRABAJADOR = 103;
	public static final int LISTAR_TRABAJADOR = 104;
	public static final int ACTUALIZAR_TRABAJADOR = 105;
	public static final int BAJA_TRABAJADOR = 106;
	
	
	
	public static final int RES_ALTA_TRABAJADOR_TIEMPO_PARCIAL_OK = 110;
	public static final int RES_ALTA_TRABAJADOR_TIEMPO_PARCIAL_KO = 111;
	public static final int RES_ALTA_TRABAJADOR_TIEMPO_COMPLETO_OK = 120;
	public static final int RES_ALTA_TRABAJADOR_TIEMPO_COMPLETO_KO = 121;
	public static final int RES_BUSCAR_TRABAJADOR_OK = 130;
	public static final int RES_BUSCAR_TRABAJADOR_KO = 131;
	public static final int RES_LISTAR_TRABAJADOR_OK = 140;
	public static final int RES_LISTAR_TRABAJADOR_KO = 141;
	public static final int RES_ACTUALIZAR_TRABAJADOR_OK = 150;
	public static final int RES_ACTUALIZAR_TRABAJADOR_KO = 151;
	public static final int BUSCAR_ACTUALIZAR_TRABAJADOR = 152;
	public static final int RES_BUSCAR_ACTUALIZAR_TRABAJADOR_OK = 153;
	public static final int RES_BUSCAR_ACTUALIZAR_TRABAJADOR_KO = 154;
	public static final int RES_BAJA_TRABAJADOR_OK = 160;
	public static final int RES_BAJA_TRABAJADOR_KO = 161;
	
	
	
	public static final int ALTA_CURSO = 201;
	public static final int BUSCAR_CURSO = 202;
	public static final int LISTAR_CURSO = 203;
	public static final int ACTUALIZAR_CURSO = 204;	
	public static final int BAJA_CURSO = 205;
	public static final int MATRICULAR_TRABAJADOR = 206;
	public static final int DESMATRICULAR_TRABAJADOR = 207;
	
	public static final int RES_ALTA_CURSO_OK = 210;
	public static final int RES_ALTA_CURSO_KO = 211;
	public static final int RES_BUSCAR_CURSO_OK = 220;
	public static final int RES_BUSCAR_CURSO_KO = 221;
	public static final int RES_LISTAR_CURSO_OK = 230;
	public static final int RES_LISTAR_CURSO_KO = 231;
	public static final int RES_ACTUALIZAR_CURSO_OK = 240;
	public static final int RES_ACTUALIZAR_CURSO_KO = 241;
	public static final int RES_BAJA_CURSO_OK = 250;
	public static final int RES_BAJA_CURSO_KO = 251;
	public static final int RES_DESMATRICULAR_TRABAJADOR_OK = 260;
	public static final int RES_DESMATRICULAR_TRABAJADOR_KO = 261;
	public static final int RES_MATRICULAR_TRABAJADOR_OK = 270;
	public static final int RES_MATRICULAR_TRABAJADOR_KO = 271;

	public static final int ALTA_DEPARTAMENTO = 301;
	public static final int BUSCAR_DEPARTAMENTO = 302;
	public static final int LISTAR_DEPARTAMENTO = 303;
	public static final int ACTUALIZAR_DEPARTAMENTO = 304;
	public static final int BAJA_DEPARTAMENTO = 305;
	public static final int CALCULAR_NOMINAS_TRABAJADOR = 306;

	public static final int RES_ALTA_DEPARTAMENTO_OK = 310;
	public static final int RES_ALTA_DEPARTAMENTO_KO = 311;
	public static final int RES_BUSCAR_DEPARTAMENTO_OK = 320;
	public static final int RES_BUSCAR_DEPARTAMENTO_KO = 321;
	public static final int RES_LISTAR_DEPARTAMENTO_OK = 330;
	public static final int RES_LISTAR_DEPARTAMENTO_KO = 331;
	public static final int RES_ACTUALIZAR_DEPARTAMENTO_OK = 340;
	public static final int RES_ACTUALIZAR_DEPARTAMENTO_KO = 341;
	public static final int RES_BAJA_DEPARTAMENTO_OK = 350;
	public static final int RES_BAJA_DEPARTAMENTO_KO = 351;
	public static final int RES_CALCULAR_NOMINAS_TRABAJADOR_OK = 360;
	public static final int RES_CALCULAR_NOMINAS_TRABAJADOR_KO = 361;

	public static final int ALTA_PRODUCTO = 401;
	public static final int BUSCAR_PRODUCTO = 402;
	public static final int LISTAR_PRODUCTO = 403;
	public static final int ACTUALIZAR_PRODUCTO = 404;
	public static final int BAJA_PRODUCTO = 405;
	
	public static final int RES_ALTA_PRODUCTO_OK = 410;
	public static final int RES_ALTA_PRODUCTO_KO = 411;
	public static final int RES_BUSCAR_PRODUCTO_OK = 420;
	public static final int RES_BUSCAR_PRODUCTO_KO = 421;
	public static final int RES_LISTAR_PRODUCTO_OK = 430;
	public static final int RES_LISTAR_PRODUCTO_KO = 431;
	public static final int RES_ACTUALIZAR_PRODUCTO_OK = 440;
	public static final int RES_ACTUALIZAR_PRODUCTO_KO = 441;
	public static final int RES_BAJA_PRODUCTO_OK = 450;
	public static final int RES_BAJA_PRODUCTO_KO = 451;
	
	public static final int ABRIR_VENTA = 501;
	public static final int BUSCAR_VENTA = 502;
	public static final int LISTAR_VENTAS = 503;
	public static final int DEVOLUCION_VENTA = 504;
	public static final int CERRAR_VENTA = 505;
	public static final int AGREGAR_PRODUCTO = 506;
	public static final int MODIFICAR_PRODUCTO = 507;
	
	public static final int RES_ABRIR_VENTA_OK = 510;
	public static final int RES_ABRIR_VENTA_KO = 511;
	public static final int RES_BUSCAR_VENTA_OK = 520;
	public static final int RES_BUSCAR_VENTA_KO = 521;
	public static final int RES_LISTAR_VENTAS_OK = 530;
	public static final int RES_LISTAR_VENTAS_KO = 531;
	public static final int RES_DEVOLUCION_VENTA_OK = 540;
	public static final int RES_DEVOLUCION_VENTA_KO = 541;
	public static final int RES_CERRAR_VENTA_OK = 550;
	public static final int RES_CERRAR_VENTA_KO = 551;
	public static final int CERRAR_VENTA_UPDATE_LINEA_VENTA = 552;
	public static final int RES_AGREGAR_PRODUCTO_OK = 560;
	public static final int RES_AGREGAR_PRODUCTO_KO = 561;
	public static final int RES_MODIFICAR_PRODUCTO_OK = 570;
	public static final int RES_MODIFICAR_PRODUCTO_KO = 571;
	
	public static final int ALTA_CLIENTE_NO_VIP = 601;
	public static final int ALTA_CLIENTE_VIP = 602;
	public static final int BUSCAR_CLIENTE = 603;
	public static final int LISTAR_CLIENTES = 604;
	public static final int ACTUALIZAR_CLIENTE = 605;
	public static final int BAJA_CLIENTE = 606;
	public static final int PRODUCTO_MAS_COMPRADO = 607;
	public static final int PRODUCTOS_MAS_COMPRADOS_VIP = 608;
	
	public static final int RES_ALTA_CLIENTE_NO_VIP_OK = 610;
	public static final int RES_ALTA_CLIENTE_NO_VIP_KO = 611;
	public static final int RES_ALTA_CLIENTE_VIP_OK = 620;
	public static final int RES_ALTA_CLIENTE_VIP_KO = 621;
	public static final int RES_BUSCAR_CLIENTE_OK = 630;
	public static final int RES_BUSCAR_CLIENTE_KO = 631;
	public static final int RES_LISTAR_CLIENTES_OK = 640;
	public static final int RES_LISTAR_CLIENTES_KO = 641;
	public static final int RES_ACTUALIZAR_CLIENTE_OK = 650;
	public static final int RES_ACTUALIZAR_CLIENTE_KO = 651;
	public static final int BUSCAR_ACTUALIZAR_CLIENTE = 652;
	public static final int RES_BUSCAR_ACTUALIZAR_CLIENTE_OK = 653;
	public static final int RES_BUSCAR_ACTUALIZAR_CLIENTE_KO = 654;
	public static final int RES_BAJA_CLIENTE_OK = 660;
	public static final int RES_BAJA_CLIENTE_KO = 661;
	public static final int RES_PRODUCTO_MAS_COMPRADO_OK = 670;
	public static final int RES_PRODUCTO_MAS_COMPRADO_KO = 671;
	public static final int RES_PRODUCTOS_MAS_COMPRADOS_VIP_OK = 680;
	public static final int RES_PRODUCTOS_MAS_COMPRADOS_VIP_KO = 681;

	public static final int CREAR_GUI_TRABAJADOR = 801;
	public static final int CREAR_GUI_CURSO = 802;
	public static final int CREAR_GUI_DEPARTAMENTO = 803;
	public static final int CREAR_GUI_PRODUCTO = 804;
	public static final int CREAR_GUI_VENTA = 805;
	public static final int CREAR_GUI_CLIENTE = 806;

	public static final int GUI_MOSTRAR = 1000;

}