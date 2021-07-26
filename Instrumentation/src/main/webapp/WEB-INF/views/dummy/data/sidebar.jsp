<a class="nav-link collapsed" id="SYS" href="#" data-toggle="collapse" data-target="#collapseS" aria-expanded="false" aria-controls="collapseS" style="width:190px;">
    <div class="sb-nav-link-icon"><i class="fas fa-tags fa-sm text-white-50"></i></div>
    시스템
    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
</a>
<div class="collapse" id="collapseS" aria-labelledby="headingOne" data-parent="#sidenavAccordion">

    <nav class="sb-sidenav-menu-nested nav">
        <a class="nav-link my-0 py-1" href="data_total.php?id=n번째&start=시작날짜&end=현날짜">
            <i class="fas fa-tag fa-sm text-white-50"></i>
            &nbsp n번쨰 시스템</a>
        <?php } ?>
    </nav>
</div>


<a class="nav-link collapsed" id="W" href="#" data-toggle="collapse" data-target="#collapseW" aria-expanded="false" aria-controls="collapseW" style="width:190px;">
    <div class="sb-nav-link-icon"><i class="fas fa-tags fa-sm text-white-50"></i></div>
    지하수위계
    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
</a>
<div class="collapse" id="collapseW" aria-labelledby="headingOne" data-parent="#sidenavAccordion">

    <nav class="sb-sidenav-menu-nested nav">
        <?php 
        for ($i=0; $i < 4; $i++) { 
        ?>
        <a class="nav-link my-0 py-1" id="<?=$sensor_name[$s[$i]][$sens_no_w[$i]]?>" href="data_w.php?id=<?=$s[$i]?>&chkAvg=<?=$chkAvg?>&select_id=<?=$sensor_name[$s[$i]][$sens_no_w[$i]]?>&start=<?=$start?>&end=<?=$end?>">
            <i class="fas fa-tag fa-sm text-white-50"></i>
            &nbsp<?=$sensor_name[$s[$i]][$sens_no_w[$i]]?></a>
        <?php 
        }
        ?>
    </nav>
</div>


<a class="nav-link collapsed" id="IPI" href="#" data-toggle="collapse" data-target="#collapseIPI" aria-expanded="false" aria-controls="collapseIPI" style="width:190px;">
    <div class="sb-nav-link-icon"><i class="fas fa-tags fa-sm text-white-50"></i></div>
    지중경사계
    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
</a>
<div class="collapse" id="collapseIPI" aria-labelledby="headingTwo" data-parent="#sidenavAccordion" style="width:190px;">
    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">

        <?php 
        for ($i=0; $i < 5; $i++) { 
        ?>
        <a class="nav-link collapsed  my-0 py-1" href="#" data-toggle="collapse" data-target="#<?=$hole_name[$i]?>" aria-expanded="false" aria-controls="<?=$hole_name[$i]?>">
            <i class="fas fa-tags fa-sm text-white-50"></i>
            &nbsp<?=$hole_name[$i]?>
            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
        </a>
        <div class="collapse" id="<?=$hole_name[$i]?>" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
            <nav class="sb-sidenav-menu-nested nav">
                <a class="nav-link my-0 py-1" id="<?=$hole_name[$i]?>_chart" href="data_ipi.php?id=<?=$i?>&start=<?=$start?>&end=<?=$end?>">누적그래프</a>
                <a class="nav-link my-0 py-1" id="<?=$hole_name[$i]?>_data" href="data_total_ipi.php?id=<?=$i?>&start=<?=$start?>&end=<?=$end?>">누적데이터</a>
                <?php for ($c=0; $c < sizeof($ipi_name[$i]); $c++) { ?>
                <a class="nav-link my-0 py-1" id="<?=$ipi_name[$i][$c]?>" href="data.php?id=<?=$i?>&select_id=<?=$ipi_name[$i][$c]?>&chkAvg=<?=$chkAvg?>&start=<?=$start?>&end=<?=$end?>"><?=$ipi_name[$i][$c]?></a>
                <?php } ?>
            </nav>
        </div>
        <?php
        }
        ?>

    </nav>
</div>