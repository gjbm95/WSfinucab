DROP FUNCTION eliminarpresupuesto(character varying, integer);
DROP FUNCTION obtenerPresupuesto(character varying);
DROP FUNCTION obtenerlistapresupuesto(integer);
DROP FUNCTION agregarpresupuesto(character varying, real, character varying, integer, integer, integer);
DROP FUNCTION verificarnombre(character varying);
DROP FUNCTION modificarpresupuesto(character varying, real, character varying, integer, integer, integer);
DROP FUNCTION obtenerlistapresupuestoasc(integer);


CREATE OR REPLACE FUNCTION agregarpresupuesto(
	nombrepresupuesto character varying,
	monto real,
	clasificacion character varying,
	duracion integer,
	usuario integer,
	categoria integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
    
AS $function$

DECLARE
 result integer;

BEGIN
  insert into presupuesto(pr_nombre, pr_monto, pr_fecha, pr_clasificacion, pr_duracion, usuariou_id, categoriaca_id)
    values (nombrePresupuesto, monto, CURRENT_TIMESTAMP, clasificacion, duracion, usuario, categoria);

    if found then
  result := 1;
  else result := 0;
  end if;
  RETURN result;
END;

$function$;


CREATE OR REPLACE FUNCTION verificarNombre(
	"nombrePresupuesto" character varying)
RETURNS bigint 
LANGUAGE 'sql' AS
$function$

select count(pr_id) from presupuesto where pr_nombre = "nombrePresupuesto";

$function$



CREATE OR REPLACE FUNCTION obtenerlistapresupuesto(
	idusuario integer,
	OUT pr_id integer,
	OUT pr_nombre character varying,
	OUT ca_nombre character varying,
	OUT pr_monto real,
	OUT pr_duracion integer,
	OUT pr_clasificacion character varying,
	OUT ca_esingreso boolean)
    RETURNS SETOF record 
    LANGUAGE 'sql'
    
AS $function$

SELECT p.pr_id, p.pr_nombre, c.ca_nombre,p.pr_monto,p.pr_duracion,p.pr_clasificacion,c.ca_esIngreso
FROM Presupuesto p, Categoria c
WHERE p.categoriaca_id = c.ca_id AND  p.usuariou_id = idUsuario

$function$;

CREATE OR REPLACE FUNCTION obtenerlistapresupuestoasc(
	idusuario integer,
	OUT pr_id integer,
	OUT pr_nombre character varying,
	OUT ca_nombre character varying,
	OUT pr_monto real,
	OUT pr_duracion integer,
	OUT pr_clasificacion character varying,
	OUT ca_esingreso boolean)
    RETURNS SETOF record 
    LANGUAGE 'sql'
    
AS $function$

SELECT p.pr_id, p.pr_nombre,c.ca_nombre,p.pr_monto,p.pr_duracion,p.pr_clasificacion,c.ca_esIngreso
FROM Presupuesto p, Categoria c
WHERE p.categoriaca_id = c.ca_id AND  p.usuariou_id = idUsuario
ORDER BY p.pr_clasificacion ASC

$function$;


CREATE OR REPLACE FUNCTION obtenerpresupuesto(
	"nombrePresupuesto" character varying,
	OUT pr_nombre character varying,
	OUT ca_id integer,
	OUT pr_monto real,
	OUT pr_clasificacion character varying)
    RETURNS record
    LANGUAGE 'sql'
    
AS $function$

select p.pr_nombre, c.ca_id, p.pr_monto, p.pr_clasificacion from Presupuesto p, Categoria c where p.categoriaca_id = c.ca_id 
and p.pr_nombre = "nombrePresupuesto"

$function$;



CREATE OR REPLACE FUNCTION eliminarPresupuesto(nombrePresupuesto character varying, idUsuario integer)
RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	DELETE FROM Presupuesto 
    WHERE  pr_nombre = nombrePresupuesto and usuariou_id = idUsuario;

    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION modificarPresupuesto(nombrepresupuesto character varying,
	monto real,
	clasificacion character varying,
	duracion integer,
	usuario integer,
	categoria integer,
	idPr integer)
    RETURNS integer LANGUAGE 'plpgsql'
    AS $$
DECLARE
result integer;
    
BEGIN 

UPDATE presupuesto SET 
					pr_nombre = nombrepresupuesto,
					pr_monto = monto,
                    pr_clasificacion = clasificacion,
                    pr_duracion = duracion,
                    categoriaca_id = categoria 
                    WHERE usuariou_id= usuario and pr_id = idPr;
    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ 