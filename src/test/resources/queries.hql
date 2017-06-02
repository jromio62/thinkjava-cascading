drop table allHospitals;

create external table
allHospitals (id string, name string, lab_type int, lab_result float, perform_date string)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
LOCATION '/thinkjava/output/allHospitals';

select * from allHospitals where id = '593125869497941d20876222';