</tbody>
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
				</div>

			</section>
			<!-- /.content -->
		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->


	<!-- jQuery 2.0.2 -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="http://localhost:8080/playground_servlet/js/bootstrap.min.js" type="text/javascript"></script>
	<!-- DATA TABES SCRIPT -->
	<script src="http://localhost:8080/playground_servlet/js/plugins/datatables/jquery.dataTables.js"
		type="text/javascript"></script>
	<script src="http://localhost:8080/playground_servlet/js/plugins/datatables/dataTables.bootstrap.js"
		type="text/javascript"></script>
	<!-- AdminLTE App -->
	<script src="http://localhost:8080/playground_servlet/js/AdminLTE/app.js" type="text/javascript"></script>

	<!-- page script -->
	<script type="text/javascript">
        $(function () {
            $("#example1").dataTable();
            $('#example2').dataTable({
                "bPaginate" : true,
                "bLengthChange" : false,
                "bFilter" : false,
                "bSort" : true,
                "bInfo" : true,
                "bAutoWidth" : false
            });
        });
    </script>

</body>
</html>