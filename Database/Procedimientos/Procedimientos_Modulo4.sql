CREATE OR REPLACE FUNCTION AgregarCategoria(
	usuariou_i integer,
	ca_nombr character varying,
	c_descripcio character varying,
	ca_esingres boolean,
	ca_eshabilitad boolean)
    RETURNS integer
    LANGUAGE 'plpgsql'
    
AS $function$

DECLARE
 result integer;

BEGIN
  INSERT INTO Pago ( usuariou_id, ca_nombre , c_descripcion , ca_esingreso , ca_eshabilitado  ) VALUES
      (usuariou_i, ca_nombr , c_descripcio , ca_esingres , ca_eshabilitad);

    if found then
  result := 1;
  else result := 0;
  end if;
  RETURN result;
END;

$function$;


CREATE OR REPLACE FUNCTION ModificarCategoria(
	ca_nombr character varying,
	c_descripcio character varying,
	ca_esingres boolean,
	ca_eshabilitad boolean,
	ca_i integer	)
    RETURNS integer
    LANGUAGE 'plpgsql'
    AS $$
DECLARE
result integer;
    
BEGIN 

UPDATE pago SET 
					ca_nombre = ca_nombr ,
					c_descripcion = c_descripcio ,
					ca_esingreso = ca_esingres ,
					ca_eshabilitado = ca_eshabilitad 
				    where ca_id=ca_i;
    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ 


CREATE OR REPLACE FUNCTION ConsultarCategoria(
	idusuario integer,
    OUT ca_id integer,
	OUT ca_nombre character varying,
	OUT c_descripcion character varying,
    OUT ca_eshabilitado boolean,
	OUT ca_esingreso boolean,
	OUT usuariou_id integer)
    RETURNS record
    LANGUAGE 'sql'
    
AS $function$


select c.ca_id, c.ca_nombre, c.c_descripcion, c.ca_eshabilitado , c.ca_esingreso , c.usuariou_id from Categoria c
where (c.ca_id = idusuario)
$function$;

CREATE OR REPLACE FUNCTION ConsultarTodos(
	idusuario integer,
    OUT ca_id integer,
	OUT ca_nombre character varying,
	OUT c_descripcion character varying,
    OUT ca_eshabilitado boolean,
	OUT ca_esingreso boolean,
	OUT usuariou_id integer)
    RETURNS record
    LANGUAGE 'sql'
    
AS $function$

select c.ca_id, c.ca_nombre, c.c_descripcion, c.ca_eshabilitado , c.ca_esingreso , c.usuariou_id from Categoria c
where (c.usuariou_id = idusuario and c.ca_id <> -1)
$function$;

CREATE OR REPLACE FUNCTION EliminarCategoria(
    idCategoria integer)
RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	DELETE FROM Categoria c 
    WHERE  c.ca_id = idCategoria;

    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

