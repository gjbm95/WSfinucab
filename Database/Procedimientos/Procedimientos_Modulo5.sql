DROP FUNCTION ConsultarPago(integer , integer);
DROP FUNCTION ListaPagos(integer);
DROP FUNCTION AgregarPago(integer, real, character varying, character varying, integer, integer );
DROP FUNCTION ModificarPago(real, character varying, character varying, integer, integer);




CREATE OR REPLACE FUNCTION AgregarPago(
	monto real,
	descripcion character varying,
	transaccion character varying,
	categoria integer,
	usuario integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
    
AS $function$

DECLARE
 result integer;

BEGIN
  INSERT INTO Pago (pg_monto ,pg_descripcion , pg_tipotransaccion , categoriaca_id, usuariou_id) VALUES
      (monto,descripcion,transaccion,categoria,usuario);

    if found then
  result := 1;
  else result := 0;
  end if;
  RETURN result;
END;

$function$;


CREATE OR REPLACE FUNCTION ModificarPago(monto real,
	descripcion character varying,
	transaccion character varying,
	categoria integer,
	usuario integer)
    RETURNS integer LANGUAGE 'plpgsql'
    AS $$
DECLARE
result integer;
    
BEGIN 

UPDATE pago SET 
					pg_monto=monto , 
					pg_descripcion=descripcion , 
					pg_tipotransaccion=transaccion , 
					categoriaca_id= categoria
				    where usuariou_id=usuario;
    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ 


CREATE OR REPLACE FUNCTION ConsultarPago(
	idpago integer,
    
	OUT pg_id integer,
	OUT pg_monto real,
	OUT pg_descripcion character varying,
	OUT pg_tipoTransaccion character varying,
	OUT categoriaca_id integer,
	OUT usuariou_id integer)
    RETURNS record
    LANGUAGE 'sql'
    
AS $function$


select p.pg_id, p.pg_monto, p.pg_descripcion, p.pg_tipotransaccion , p.categoriaca_id , p.usuariou_id from Pago p
where ( p.pg_id = idpago)
$function$;




CREATE OR REPLACE FUNCTION ListaPagos(
	idusuario integer,
	OUT pg_id integer,
	OUT pg_monto real,
	OUT pg_descripcion character varying,
	OUT pg_tipotransaccion character varying,
	OUT categoriaca_id integer,
	OUT usuariou_id integer)
    RETURNS SETOF record 
    LANGUAGE 'sql'
    
AS $function$

select p.pg_id, p.pg_monto, p.pg_descripcion, p.pg_tipotransaccion , p.categoriaca_id , p.usuariou_id from Pago p
where (p.usuariou_id = idusuario)

$function$;