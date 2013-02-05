
select * from team where team_Id = 560;
select * from season_division_team where team_id = 560;

select ssn_num, count(*)
from fixture
group by ssn_num;

select distinct div_id
from fixture
where ssn_num = 2011;

select distinct d.div_id, div_name
from fixture f, division d
where f.ssn_num = 1999
and d.div_id = f.div_id;

select count(*)
from fixture
where home_goals is null
and fixture_date < now();

select div_id, count(distinct away_team_id)
from fixture
where ssn_num = 2001
group by div_id;


select * from fixture where div_id = 34;

select f.away_team_id, t.team_name, count(*)
from fixture f, team t
where f.ssn_num = 2002
and f.div_id = 30
and t.team_id = f.away_team_id
group by f.away_team_id, t.team_name;

select * from fixture f
where f.ssn_num = 2002
and f.div_id = 30
and f.away_team_id = 667;

delete from fixture where fixture_id in (40801, 40803, 40804);



select distinct ssn_num, div_id
from fixture
;

insert into season_division
select distinct ssn_num, div_id, 1
from fixture
;

update season_division
set div_pos = 1
where div_id = 33;

update season_division
set div_pos = 2
where div_id = 30;

update season_division
set div_pos = 3
where div_id = 31;

update season_division
set div_pos = 4
where div_id = 32;

insert into season_division_team
select distinct ssn_num, div_id, home_team_id
from fixture
;

insert into fixture (ssn_num, div_id, home_team_id, away_team_id)
select h.ssn_num, h.div_id, h.team_id, a.team_id
from season_division_team h,
     season_division_team a
where h.ssn_num = a.ssn_num
and h.div_id = a.div_id
and h.team_id != a.team_id
and not exists (select 'x' from fixture f where f.ssn_num = h.ssn_num and f.div_id = h.div_id and f.home_team_id = h.team_id and f.away_team_id = a.team_id)
;

select count(*) from fixture where fixture_date is null;


select * from fixture f, team h, team a where f.ssn_num = 2012 and h.team_id = f.home_team_id and a.team_id = f.away_team_id and h.team_id = 561 and a.team_id = 585;

select * from fixture f, team h, team a where f.fixture_date is null and h.team_id = f.home_team_id and a.team_id = f.away_team_id;

select ssn_num, div_id, home_team_id, away_team_id, count(*)
from fixture
where ssn_num = 2012
group by ssn_num, div_id, home_team_id, away_team_id
having count(*) > 1;

select * from fixture where ssn_num = 2000 and div_id = 31 and home_team_id = 578 and away_team_id = 597;




select * from fixture
where 
((home_team_id = 560 and away_team_Id = 574))
and ssn_num = 2012
;

delete from fixture where fixture_id = 21373;

