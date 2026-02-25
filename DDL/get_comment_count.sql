CREATE OR REPLACE FUNCTION public.get_comment_count(parent_id bigint)
	RETURNS bigint
	LANGUAGE plpgsql
AS $function$
BEGIN
    RETURN (SELECT COUNT(*) FROM messages WHERE parent = parent_id);
END;
$function$;;