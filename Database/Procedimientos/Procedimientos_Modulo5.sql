DROP FUNCTION ConsultarPago(integer);
DROP FUNCTION ListaPagos(integer);
DROP FUNCTION AgregarPago(integer, real, character varying, character varying, integer );
DROP FUNCTION ModificarPago(real, character varying, character varying, integer);




CREATE OR REPLACE FUNCTION AgregarPago(
	monto real,
	descripcion character varying,
	transaccion character varying,
	categoria integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
    
AS $function$

DECLARE
 result integer;

BEGIN
  INSERT INTO Pago (pg_monto ,pg_descripcion , pg_tipotransaccion , categoriaca_id) VALUES
      (monto,descripcion,transaccion,categoria);

    if found then
  result := 1;
  else result := 0;
  end if;
  RETURN result;
END;

$function$;


CREATE OR REPLACE FUNCTION ModificarPago(pago integer, monto real,
	descripcion character varying,
	transaccion character varying,
	categoria integer)
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
				    where pg_id = pago;
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
	OUT categoriaca_id integer)
    RETURNS record
    LANGUAGE 'sql'
    
AS $function$


select p.pg_id, p.pg_monto, p.pg_descripcion, p.pg_tipotransaccion , p.categoriaca_id  from Pago p
where ( p.pg_id = idpago)
$function$;




CREATE OR REPLACE FUNCTION ListaPagos(
	idusuario integer,
	OUT pg_id integer,
	OUT pg_monto real,
	OUT pg_descripcion character varying,
	OUT pg_tipotransaccion character varying,
	OUT categoriaca_id integer)
    RETURNS SETOF record 
    LANGUAGE 'sql'
    
AS $function$

select p.pg_id, p.pg_monto, p.pg_descripcion, p.pg_tipotransaccion , p.categoriaca_id  from Pago p , Categoria c , Usuario u
where (p.categoriaca_id = c.ca_id and c.usuariou_id =idusuario )

$function$;